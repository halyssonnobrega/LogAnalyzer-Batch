package br.com.batch.util;

public enum DurationEnum {

	HOURLY(60),
	DAILY(1);

	public int valor;

	DurationEnum(int valor) {
		this.valor = valor;
	}

}
