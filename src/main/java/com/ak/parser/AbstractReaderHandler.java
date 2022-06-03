package com.ak.parser;

public abstract class AbstractReaderHandler<R, H> implements ReaderHandler<R, H>{

	private ReaderConfig readerConfig;
	
	public void setReaderConfig(ReaderConfig readerConfig) {
		this.readerConfig = readerConfig;
	}
	
	public ReaderConfig getReaderConfig() {
		return readerConfig;
	}
	
	
}
