package com.ak.parser;

public final class Configuration {

	public static I_Institute getConfig(ReaderType type) {
	
		I_Institute config = null;
		switch (type) {
		case EXCEL: {
			config = ExcelReaderConfigBuilder.getInstance(type);
			break;
		}
		case PDF: {
			break;
		}
		default:
			break;
		}
		return config;

	}
}
