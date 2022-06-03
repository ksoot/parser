package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public final class ExcelReaderConfig implements ReaderConfig {

	private ReaderType readerType;
	private Institute institute;
	private Product product;
	private ReaderExceptionHandler exceptionHandler;
	private ReaderHandler<SheetRecord, Sheet> sheetHandler;
	private ReaderHandler<RowRecord, Row> rowHandler;
	private ReaderHandler<ColumnRecord, Cell> columnHandler;

	public ExcelReaderConfig(ReaderType readerType, Institute institute, Product product,
			ReaderExceptionHandler exceptionHandler, ReaderHandler<SheetRecord, Sheet> sheetHandler,
			ReaderHandler<RowRecord, Row> rowHandler, ReaderHandler<ColumnRecord, Cell> columnHandler) {
		this.readerType = readerType;
		this.product = product;
		this.institute = institute;
		this.exceptionHandler = exceptionHandler;
		this.sheetHandler = sheetHandler;
		this.rowHandler = rowHandler;
		this.columnHandler = columnHandler;
	}

	@Override
	public ReaderType getConfigType() {
		return readerType;
	}

	public ReaderType getReaderType() {
		return readerType;
	}

	public Institute getInstitute() {
		return institute;
	}

	public Product getProduct() {
		return product;
	}

	public ReaderExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}


	public ReaderHandler<SheetRecord, Sheet> getSheetHandler() {
		sheetHandler.setReaderConfig(this);
		return sheetHandler;
	}

	public ReaderHandler<RowRecord, Row> getRowHandler() {
		rowHandler.setReaderConfig(this);
		return rowHandler;
	}

	public ReaderHandler<ColumnRecord, Cell> getColumnHandler() {
		rowHandler.setReaderConfig(this);
		return columnHandler;
	}

}
