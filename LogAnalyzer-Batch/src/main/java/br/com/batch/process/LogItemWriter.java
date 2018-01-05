package br.com.batch.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.batch.entity.Log;
import br.com.batch.repository.LogRepository;

public class LogItemWriter implements ItemWriter<Log> {
	private static final Logger logger = LoggerFactory.getLogger(LogItemWriter.class);

	@Autowired
	private LogRepository logRepository;

	public LogItemWriter() {
	}

	public LogItemWriter(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public void write(List<? extends Log> items) throws Exception {
		logger.info("BATCH JOB WRITER");

		logger.info("INSERTING " + items.size() + " RECORDS...");

		for (Log log : items) {
			logRepository.save(log);
		}
	}

}
