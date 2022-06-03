package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;

public final class ExcelColumnHandler extends AbstractReaderHandler<ColumnRecord, Cell> {

	private final ExcelColumnParser parser;

	public ExcelColumnHandler(ExcelColumnParser parser) {
		this.parser = parser;
	}
	

	@Override
	public ColumnRecord handle(Cell cell) {
		ColumnRecord columnRecord = null;
		try {
			switch (cell.getCellType()) {

			case NUMERIC:
				columnRecord = parser.processNumericCell(cell);
				break;
			case STRING:
				columnRecord = parser.processStringCell(cell);
				break;
			case BLANK:
				columnRecord = parser.processBlankCell(cell);
				break;
			case BOOLEAN:
				columnRecord = parser.processBooleanCell(cell);
				break;
			case FORMULA:
				columnRecord = parser.processFormulaCell(cell);
				break;
			default:
				columnRecord = null;
				break;
			}
		} catch (Exception e) {
			getReaderConfig().getExceptionHandler().handleException(e, cell);
		}
		return columnRecord;
	}

	public ExcelReaderConfig getReaderConfig() {
		return (ExcelReaderConfig) super.getReaderConfig();
	}
	
}
