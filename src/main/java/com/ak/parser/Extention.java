package com.ak.parser;

import java.util.Arrays;
import java.util.List;

public enum Extention {

	XLSX("xlsx", "XLSX"), XLS("xls", "XLS"), UNSUPPORTED("Not Supported");

	private final List<String> values;

	Extention(String... values) {
		this.values = Arrays.asList(values);
	}

	public List<String> getValues() {
		return values;
	}

	public static Extention find(String name) {
		for (Extention ext : Extention.values()) {
			if (ext.getValues().contains(name)) {
				return ext;
			} 
		}
		return Extention.UNSUPPORTED;
	}
}
