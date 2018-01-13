package br.com.batch.process;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import br.com.batch.configuration.LogSystem;
import br.com.batch.model.dto.LogDTO;
import br.com.batch.model.entity.BlockedIpEntity;
import br.com.batch.model.entity.LogEntity;
import br.com.batch.model.repository.BlockedIpRepository;
import br.com.batch.model.repository.LogRepository;
import br.com.batch.util.DateFormat;
import br.com.batch.util.DurationEnum;

public class LogItemWriter implements ItemWriter<LogDTO> {
	private static final Logger logger = LoggerFactory.getLogger(LogItemWriter.class);

	private LogRepository logRepository;

	private BlockedIpRepository blockedIpRepository;

	public LogItemWriter(LogRepository logRepository, BlockedIpRepository blockedIpRepository) {
		this.logRepository = logRepository;
		this.blockedIpRepository = blockedIpRepository;
	}

	@Override
	public void write(List<? extends LogDTO> items) throws Exception {
		logger.info("BATCH JOB WRITER");

		logger.info("INSERTING " + items.size() + " RECORDS...");

		List<LogEntity> listLog = logRepository.findAll(); // TODO: AJUST QUERY

		Collections.sort(listLog, new LogEntity());

		Map<String, BlockedIpEntity> map = new HashMap<String, BlockedIpEntity>();

		for (LogEntity log : listLog) {
			if ( (LogSystem.startDate != null && dateIsValid(log.getDate())) 
					|| LogSystem.startDate == null) {
				
				String key = log.getIp();
				
				if (!map.containsKey(key)) {
					BlockedIpEntity ip = new BlockedIpEntity();
					ip.setIp(log.getIp());
					ip.setQuantity(1);
					map.put(log.getIp(), ip);
				} else {
					BlockedIpEntity ip = map.get(key);
					Integer quantity = ip.getQuantity() + 1;
					ip.setQuantity(quantity);
				}
			}
		}

		for (String key : map.keySet()) {
			BlockedIpEntity BlockedIp = map.get(key);
			if (BlockedIp.getQuantity() > LogSystem.threshold) {
				BlockedIp.setMessage("IP is blocked");
				System.out.println("IP: " + BlockedIp.getIp());
				System.out.println("Quantity: " + BlockedIp.getQuantity());
				System.out.println(BlockedIp.getMessage());
				System.out.println("##########################################");

				blockedIpRepository.save(BlockedIp);
			}
		}
	}

	private boolean dateIsValid(String date) {
		boolean isValid = false;
		
		String dateFormat = date.substring (0, date.length() - 4);
		Date dt = DateFormat.parseStringToDate(dateFormat);
		
		Date startDate = DateFormat.parseStringToDate(LogSystem.startDate.replace(".", " "));
		Date endDate = DateFormat.parseStringToDate(LogSystem.startDate.replace(".", " "));
				
		if (LogSystem.duration != null) {			
			if (LogSystem.duration.toUpperCase().equals(DurationEnum.DAILY.name())) {
				endDate = DateFormat.addDay(endDate, DurationEnum.DAILY);
			} else if (LogSystem.duration.toUpperCase().equals(DurationEnum.HOURLY.name())) {
				endDate = DateFormat.addHour(endDate, DurationEnum.HOURLY);
			}
		}
		
		if (dt.compareTo(startDate) >= 0  && dt.compareTo(endDate) <= 0) {
			logger.info("Requests starting from " + startDate + " to " + endDate + " | " + "Date : " + dt);
			
			isValid = true;
		}
		
		return isValid;
	}
	
	
}
