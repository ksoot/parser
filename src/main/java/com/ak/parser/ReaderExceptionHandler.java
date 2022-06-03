package com.ak.parser;

public interface ReaderExceptionHandler {

	<T> void handleException(Exception ex, T type);
	
}
