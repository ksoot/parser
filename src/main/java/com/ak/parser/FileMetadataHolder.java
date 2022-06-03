package com.ak.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class FileMetadataHolder {

	private FileMetadata header;

	private FileMetadataHolder() {
		
		YAMLReader<FileMetadata> headerReader = new YAMLReader<FileMetadata>(FileMetadata.class);

		try {
			header = headerReader.read("headers.yaml");
		} catch (IOException e) {
			System.err.println("Header is mandatory to run the application. Please check the error and try again");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static FileMetadataHolder getInstance() {
		return new FileMetadataHolder();
	}
	
	public Map<String, List<String>> headers() {
		return header.getHeaders();
	}
	
	public Map<String, List<String>> footers() {
		return header.getFooters();
	}
	
	public Map<String, String> narration() {
		return header.getNarration();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new RuntimeException("clone not supported");
	}

}
