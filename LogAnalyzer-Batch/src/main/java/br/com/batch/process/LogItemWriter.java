package br.com.batch.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import br.com.batch.model.LogDTO;
import br.com.batch.repository.LogRepository;

public class LogItemWriter implements ItemWriter<LogDTO> {
	private static final Logger logger = LoggerFactory.getLogger(LogItemWriter.class);

	private LogRepository logRepository;	

	public LogItemWriter() {
		logRepository = new LogRepository();
	}

	public LogItemWriter(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public void write(List<? extends LogDTO> items) throws Exception {
		logger.info("BATCH JOB WRITER");

		logger.info("INSERTING " + items.size() + " RECORDS...");

		for (LogDTO log : items) {
			logRepository.save(LogBuilder.getLogEntity(log));
		}
	}

}
