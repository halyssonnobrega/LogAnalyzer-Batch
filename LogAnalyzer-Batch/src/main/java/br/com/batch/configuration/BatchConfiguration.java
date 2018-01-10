package br.com.batch.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import br.com.batch.model.LogDTO;
import br.com.batch.process.JobCompletionListener;
import br.com.batch.process.LogFieldSetMapper;
import br.com.batch.process.LogItemProcessor;
import br.com.batch.process.LogItemWriter;
import br.com.batch.repository.BlockedIpRepository;
import br.com.batch.repository.LogRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
	@Autowired
	private LogRepository logRepository;

	@Autowired
	private BlockedIpRepository blockedIpRepository;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job(Step step1) throws Exception {
		return jobBuilderFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<LogDTO, LogDTO>chunk(100000000)
				.reader(reader())
				.processor(new LogItemProcessor(logRepository, blockedIpRepository))
				.writer(new LogItemWriter(logRepository, blockedIpRepository))
				.build();
	}
	
	@Bean
	public ItemReader<LogDTO> reader() {
    	logger.info("BATCH JOB READER");
		FlatFileItemReader<LogDTO> reader = new FlatFileItemReader<LogDTO>();
		reader.setResource(new ClassPathResource("access.log"));
		reader.setLineMapper(lineMapper());
		return reader;
	}
	
	public LineMapper<LogDTO> lineMapper() {
		DefaultLineMapper<LogDTO> lineMapper = new DefaultLineMapper<LogDTO>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter("|");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[]{"date", "ip", "request", "status", "userAgent"});

		BeanWrapperFieldSetMapper<LogDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<LogDTO>();
		fieldSetMapper.setTargetType(LogDTO.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(logFieldSetMapper());

		return lineMapper;
	}

	public LogFieldSetMapper logFieldSetMapper() {
		return new LogFieldSetMapper();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

	public LogRepository getLogRepository() {
		return logRepository;
	}

	public void setLogRepository(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	public BlockedIpRepository getBlockedIpRepository() {
		return blockedIpRepository;
	}

	public void setBlockedIpRepository(BlockedIpRepository blockedIpRepository) {
		this.blockedIpRepository = blockedIpRepository;
	}
}