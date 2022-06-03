package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;

public class SimpleColumnParser implements ExcelColumnParser {

	@Override
	public ColumnRecord processNumericCell(Cell cell)
			throws ReaderException {
		ColumnRecord numericRecord;
		if (ReaderUtil.isHeaderIdentified()) {
			numericRecord = new ColumnRecord(cell.getColumnIndex(), ReaderUtil.headersExtractedFromDoc.get(cell.getColumnIndex()),
					cell.getNumericCellValue());

		} else {
			numericRecord = new ColumnRecord(cell.getColumnIndex(), null, cell.getNumericCellValue());

		}

		return numericRecord;
	}

	@Override
	public ColumnRecord processStringCell(Cell cell)
			throws ReaderException {
		ColumnRecord stringRecord;
		if (ReaderUtil.isHeaderIdentified()) {
			stringRecord = new ColumnRecord(cell.getColumnIndex(), ReaderUtil.headersExtractedFromDoc.get(cell.getColumnIndex()),
					cell.getStringCellValue());

		} else {
			stringRecord = new ColumnRecord(cell.getColumnIndex(), null, cell.getStringCellValue());

		}
		return stringRecord;
	}

	@Override
	public ColumnRecord processBooleanCell(Cell cell)
			throws ReaderException {
		ColumnRecord booleanRecord;
		if (ReaderUtil.isHeaderIdentified()) {
			booleanRecord = new ColumnRecord(cell.getColumnIndex(), ReaderUtil.headersExtractedFromDoc.get(cell.getColumnIndex()),
					cell.getBooleanCellValue());

		} else {
			booleanRecord = new ColumnRecord(cell.getColumnIndex(), null, cell.getBooleanCellValue());

		}
		return booleanRecord;
	}

	@Override
	public ColumnRecord processFormulaCell(Cell cell)
			throws ReaderException {
		ColumnRecord formulaRecord;
		if (ReaderUtil.isHeaderIdentified()) {
			formulaRecord = new ColumnRecord(cell.getColumnIndex(), ReaderUtil.headersExtractedFromDoc.get(cell.getColumnIndex()),
					cell.getCellFormula());

		} else {
			formulaRecord = new ColumnRecord(cell.getColumnIndex(), null, cell.getCellFormula());

		}
		return formulaRecord;
	}

	@Override
	public ColumnRecord processBlankCell(Cell cell)
			throws ReaderException {
		ColumnRecord blankRecord = null;
		if (ReaderUtil.isHeaderIdentified() && ReaderUtil.headersExtractedFromDoc.get(cell.getColumnIndex()) != null) {
			blankRecord = new ColumnRecord(cell.getColumnIndex(), ReaderUtil.headersExtractedFromDoc.get(cell.getColumnIndex()),
					cell.getStringCellValue());

		} else {
			return null;

		}
		return blankRecord;

	}

}
