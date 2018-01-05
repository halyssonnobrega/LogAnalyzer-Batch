package br.com.batch.process;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.batch.entity.Log;

public class LogFieldSetMapper implements FieldSetMapper<Log> {

	@Override
	public Log mapFieldSet(FieldSet fieldSet) throws BindException {

		Log log = new Log();

		log.setDate(fieldSet.readString("date"));
		log.setIp(fieldSet.readString("ip"));
		log.setRequet(fieldSet.readString("request"));
		log.setStatus(fieldSet.readString("status"));
		log.setUserAgent(fieldSet.readString("userAgent"));
		
		return log;
	}

}
