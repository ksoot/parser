package com.ak.parser;

public interface ReaderHandler<R, H> {

	R handle(H type);

	public void setReaderConfig(ReaderConfig readerConfig);

	public ReaderConfig getReaderConfig();

}
