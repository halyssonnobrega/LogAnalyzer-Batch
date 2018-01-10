package br.com.batch.process;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.batch.model.LogDTO;

public class LogFieldSetMapper implements FieldSetMapper<LogDTO> {

	@Override
	public LogDTO mapFieldSet(FieldSet fieldSet) throws BindException {

		LogDTO log = new LogDTO();

		log.setDate(fieldSet.readString("date"));
		log.setIp(fieldSet.readString("ip"));
		log.setRequest(fieldSet.readString("request"));
		log.setStatus(fieldSet.readString("status"));
		log.setUserAgent(fieldSet.readString("userAgent"));
		
		return log;
	}

}
