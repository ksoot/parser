package com.ak.parser;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

public interface ColumnParser {

	ColumnRecord parse(Cell cell, Map<Integer, String> headersExtractedFromDoc) throws ReaderException;

	
}
