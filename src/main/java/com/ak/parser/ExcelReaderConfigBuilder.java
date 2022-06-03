package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelReaderConfigBuilder implements I_Institute, I_Product, I_ExceptionHandler,
		I_SheetHandler, I_RowHandler, I_ColumnHandler, I_ConfigBuilder {

	private ReaderType readerType;
	private Institute institute;
	private Product product;
	private ReaderExceptionHandler exceptionHandler;
	private ReaderHandler<SheetRecord, Sheet> sheetHandler;
	private ReaderHandler<RowRecord, Row> rowHandler;
	private ReaderHandler<ColumnRecord, Cell> columnHandler;

	private ExcelReaderConfigBuilder() {
	}

	private ExcelReaderConfigBuilder(ReaderType readerType) {
		this.readerType = readerType;
	}


	@Override
	public I_SheetHandler product(Product product) {
		this.product = product;
		return this;
	}

	@Override
	public I_Product institute(Institute institute) {
		this.institute = institute;
		return this;
	}

	public static I_Institute getInstance(ReaderType readerType) {
		return new ExcelReaderConfigBuilder(readerType);
	}

	public ReaderConfig build() {
		return new ExcelReaderConfig(readerType, institute, product, exceptionHandler, sheetHandler, rowHandler, columnHandler);
	}

	@Override
	public I_ExceptionHandler columnHandler(ReaderHandler<ColumnRecord, Cell> handler) {
		this.columnHandler = handler;
		return this;
	}

	@Override
	public I_ColumnHandler rowHandler(ReaderHandler<RowRecord, Row> handler) {
		this.rowHandler = handler;
		return this;
	}

	@Override
	public I_RowHandler sheetHandler(ReaderHandler<SheetRecord, Sheet> handler) {
		this.sheetHandler = handler;
		return this;
	}

	@Override
	public I_ConfigBuilder exceptionHandler(ReaderExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
		return this;
	}
}
