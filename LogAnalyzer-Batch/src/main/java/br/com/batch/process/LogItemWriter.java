package br.com.batch.process;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import br.com.batch.entity.BlockedIpEntity;
import br.com.batch.entity.LogEntity;
import br.com.batch.model.LogDTO;
import br.com.batch.repository.BlockedIpRepository;
import br.com.batch.repository.LogRepository;

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
		
		List<LogEntity> listLog = logRepository.findAll();
		
		Collections.sort(listLog, new LogEntity());
		
		Map<String, BlockedIpEntity> map = new HashMap<String, BlockedIpEntity>();
		
		for(LogEntity log : listLog) {
			String key = log.getIp();
			
			if(!map.containsKey(key)) {
				BlockedIpEntity ip = new BlockedIpEntity();
				ip.setIp(log.getIp());
				ip.setQuantity(1);
				map.put(log.getIp(), ip);
			} else {
				BlockedIpEntity ip = map.get(key);
				Integer quantity = ip.getQuantity()+1;
				ip.setQuantity(quantity);
			}
		}
		
		for(String key : map.keySet()) {
			BlockedIpEntity BlockedIp = map.get(key);
			if (BlockedIp.getQuantity() > 100) {
				BlockedIp.setMessage("IP is blocked");
				System.out.println("IP: " + BlockedIp.getIp());
				System.out.println("Quantity: " + BlockedIp.getQuantity());
				System.out.println(BlockedIp.getMessage());
				System.out.println("##########################################");
				
				blockedIpRepository.save(BlockedIp);
			}
		}
		
	}

}
