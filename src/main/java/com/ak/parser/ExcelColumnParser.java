package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;

public interface ExcelColumnParser {

	ColumnRecord processNumericCell(Cell cell) throws ReaderException;

	ColumnRecord processStringCell(Cell cell) throws ReaderException;

	ColumnRecord processBooleanCell(Cell cell) throws ReaderException;

	ColumnRecord processFormulaCell(Cell cell) throws ReaderException;

	ColumnRecord processBlankCell(Cell cell) throws ReaderException;

	
}
