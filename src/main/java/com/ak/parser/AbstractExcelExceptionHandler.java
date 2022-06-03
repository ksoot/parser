package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractExcelExceptionHandler implements ReaderExceptionHandler {

	@Override
	public <T> void handleException(Exception e, T type) {
		if (type instanceof Sheet) {
			Sheet sheet = (Sheet) type;
			handleSheetException(e, sheet);
		} else if (type instanceof Row) {
			Row row = (Row) type;
			handleRowException(e, row);
		} else if (type instanceof Cell) {
			Cell cell = (Cell) type;
			handleColumnException(e, cell);
		}
	}

	abstract void handleSheetException(Exception e, Sheet sheet);

	abstract void handleRowException(Exception e, Row row);

	abstract void handleColumnException(Exception e, Cell cell);

}
