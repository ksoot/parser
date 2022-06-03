package com.ak.parser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public final class YAMLReader<T> implements Reader {

	private final Class<T> type;

	public YAMLReader(Class<T> type) {
		this.type = type;
	}

	public final T read(String path) throws IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file;
		T obj = null;
		try {
			file = new File(classLoader.getResource(path).getFile());
		} catch (Exception e) {
			System.err.println("Problem with loading file: " + path);
			throw new RuntimeException(e);
		}
		
		ObjectMapper om = new ObjectMapper(new YAMLFactory());

		try {
			obj = om.readValue(file, type);
		} catch (Exception e) {
			System.err.println("Problem while creating bean: " + type.getTypeName());
			throw new RuntimeException(e);
		}

		return obj;
	}

	@Override
	public void configure(ReaderConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReaderConfig resetConfig(ReaderConfig newConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReaderConfig getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
