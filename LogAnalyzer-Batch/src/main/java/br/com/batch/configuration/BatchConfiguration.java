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

import br.com.batch.entity.Log;
import br.com.batch.process.JobCompletionListener;
import br.com.batch.process.LogFieldSetMapper;
import br.com.batch.process.LogItemProcessor;
import br.com.batch.process.LogItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
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
				.<Log, Log>chunk(10)
				.reader(reader())
				.processor(new LogItemProcessor())
				.writer(new LogItemWriter())
				.build();
	}
	
	@Bean
	public ItemReader<Log> reader() {
    	logger.info("BATCH JOB READER");
		FlatFileItemReader<Log> reader = new FlatFileItemReader<Log>();
		reader.setResource(new ClassPathResource("access.log"));
		reader.setLineMapper(lineMapper());
		return reader;
	}
	
	public LineMapper<Log> lineMapper() {
		DefaultLineMapper<Log> lineMapper = new DefaultLineMapper<Log>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter("|");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[]{"date", "ip", "request", "status", "userAgent"});

		BeanWrapperFieldSetMapper<Log> fieldSetMapper = new BeanWrapperFieldSetMapper<Log>();
		fieldSetMapper.setTargetType(Log.class);

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
}