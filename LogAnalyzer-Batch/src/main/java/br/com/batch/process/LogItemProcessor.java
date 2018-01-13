package br.com.batch.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.batch.model.dto.LogDTO;
import br.com.batch.model.repository.BlockedIpRepository;
import br.com.batch.model.repository.LogRepository;

public class LogItemProcessor implements ItemProcessor<LogDTO, LogDTO> {
	private static final Logger logger = LoggerFactory.getLogger(LogItemProcessor.class);

	private LogDTO transformedLog;

	private LogRepository logRepository;

	private BlockedIpRepository blockedIpRepository;

	public LogItemProcessor(LogRepository logRepository, BlockedIpRepository blockedIpRepository) {
		this.logRepository = logRepository;
		this.setBlockedIpRepository(blockedIpRepository);

		logger.info("DELETING ALL RECORDS...");

		logRepository.deleteAll();
		blockedIpRepository.deleteAll();
	}

	@Override
	public LogDTO process(final LogDTO log) throws Exception {
		logger.info("BATCH JOB PROCESS");

		final String date = log.getDate();
		final String ip = log.getIp().toUpperCase();
		final String request = log.getRequest().toUpperCase();
		final String status = log.getStatus().toUpperCase();
		final String userAgent = log.getUserAgent().toUpperCase();

		transformedLog = new LogDTO(date, ip, request, status, userAgent);
		logger.info("CONVERTING AND SAVE RECORD (" + log + ") INTO (" + transformedLog + ")");

		logRepository.save(LogBuilder.getLogEntity(log));

		return transformedLog;
	}

	public BlockedIpRepository getBlockedIpRepository() {
		return blockedIpRepository;
	}

	public void setBlockedIpRepository(BlockedIpRepository blockedIpRepository) {
		this.blockedIpRepository = blockedIpRepository;
	}

	public LogRepository getLogRepository() {
		return logRepository;
	}

	public void setLogRepository(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

}
