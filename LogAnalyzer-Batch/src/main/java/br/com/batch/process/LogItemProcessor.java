package br.com.batch.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.batch.model.LogDTO;

public class LogItemProcessor implements ItemProcessor<LogDTO, LogDTO> {
    private static final Logger logger = LoggerFactory.getLogger(LogItemProcessor.class);

    private LogDTO transformedLog;
    
	@Override
    public LogDTO process(final LogDTO log) throws Exception {
		logger.info("BATCH JOB PROCESS");

        final String date = log.getDate().toUpperCase();
        final String ip = log.getIp().toUpperCase();
        final String request = log.getRequet().toUpperCase();
        final String status = log.getStatus().toUpperCase();
        final String userAgent = log.getUserAgent().toUpperCase();
        
		transformedLog = new LogDTO(date, ip, request, status, userAgent);
		logger.info("Converting (" + log + ") into (" + transformedLog + ")");

        return transformedLog;
    }
}
