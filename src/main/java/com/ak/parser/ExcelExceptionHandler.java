package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelExceptionHandler extends AbstractExcelExceptionHandler{

	@Override
	void handleSheetException(Exception e, Sheet sheet) {
		e.printStackTrace();;
		
	}

	@Override
	void handleRowException(Exception e, Row row) {
		System.err.println("Problem with row at row no. :-> " + row.getRowNum() + " , row data: " + row.toString());
		e.printStackTrace();
		throw new RuntimeException(e);
	}

	@Override
	void handleColumnException(Exception e, Cell cell) {
		System.err.println(
				"Problem with column at no. :-> " + cell.getColumnIndex() + " , column data: " + cell.toString());
		e.printStackTrace();
		throw new RuntimeException(e);

	}

}
