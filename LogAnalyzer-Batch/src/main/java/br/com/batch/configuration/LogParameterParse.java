package br.com.batch.configuration;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class LogParameterParse {

	@Parameter(names = { "-accesslog", "--accesslog" }, description = "Accesslog", required = true)
	private String accesslog;

	@Parameter(names = { "-startDate", "--startDate" }, description = "Start Date")
	private String startDate;

	@Parameter(names = { "-duration", "--duration" }, description = "Duration")
	private String duration;

	@Parameter(names = { "-threshold", "--threshold" }, description = "Threshold")
	private int threshold;

	public void setSystemParameters(String[] args) {
		LogParameterParse parameter = new LogParameterParse();

		JCommander.newBuilder().addObject(parameter).build().parse(args);

		LogSystem.accesslog = parameter.accesslog;
		LogSystem.startDate = parameter.startDate;
		LogSystem.duration = parameter.duration;
		LogSystem.threshold = parameter.threshold;
	}
}
