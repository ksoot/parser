package com.ak.parser;

public class ReaderFactory {

	public static Reader getInstance(ReaderType readerType) {

		Reader reader = null;
		
		switch (readerType) {
		case EXCEL: {
			reader = new ExcelReader();
			break;
		}
		case PDF: {

			break;
		}
		case UNSUPPORTED: {
			throw new RuntimeException("This reader is not available for type: "+readerType);
		}
		default:
			throw new RuntimeException("This reader is not available for type: "+readerType);
		}

		return reader;
	}

}
