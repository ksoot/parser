package com.ak.parser;

import java.io.IOException;

public interface Reader {

	public <R> R read(String filePath) throws IOException;

	public void configure(ReaderConfig config);
	
	public ReaderConfig resetConfig(ReaderConfig newConfig);

	public ReaderConfig getConfig(); 

}
