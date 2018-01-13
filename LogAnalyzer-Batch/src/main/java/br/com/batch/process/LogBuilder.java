package br.com.batch.process;

import br.com.batch.model.dto.LogDTO;
import br.com.batch.model.entity.LogEntity;

public abstract class LogBuilder {

	private static LogEntity logEntity = null;
	
	public static LogEntity getLogEntity(LogDTO logDTO) {

		logEntity = new LogEntity();
		
		logEntity.setDate(logDTO.getDate());
		logEntity.setIp(logDTO.getIp());
		logEntity.setRequest(logDTO.getRequest());
		logEntity.setStatus(logDTO.getStatus());
		logEntity.setUserAgent(logDTO.getUserAgent());

		return logEntity;
	}
	
	
}
