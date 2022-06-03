package com.ak.parser;

import java.util.Optional;

public class Utils {

	public static Extention getExtention(String filePath) {
		Optional<String> opt = Optional.ofNullable(filePath).filter(f -> f.contains("."))
				.map(f -> f.substring(filePath.lastIndexOf(".") + 1));

		if (opt.isPresent()) {
			return Extention.find(opt.get());
		} else {
			throw new RuntimeException("File does not contain extention or invalid file name");
		}
	}

	
	public static ReaderType readerType(String filePath) {
		Extention fileExtention = getExtention(filePath);
		ReaderType readerType = ReaderType.UNSUPPORTED;
		switch (fileExtention) {
		case XLS:{
			readerType = ReaderType.EXCEL;
			break;
		} 
		case XLSX:{
			readerType = ReaderType.EXCEL;
			break;
		}
		case UNSUPPORTED:{
			readerType = ReaderType.UNSUPPORTED;
			break;
		}
		default:
			readerType = ReaderType.UNSUPPORTED;
			break;
		}		
		
		return readerType;
	}
}
