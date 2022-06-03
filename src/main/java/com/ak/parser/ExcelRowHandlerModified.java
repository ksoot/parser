package com.ak.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelRowHandlerModified extends AbstractReaderHandler<RowRecord, Row> {

	public ExcelRowHandlerModified() {
	}

	@Override
	public RowRecord handle(Row row) {

		RowRecord record = null;
		boolean allCellNull = false;
		List<ColumnRecord> list = new ArrayList<ColumnRecord>();
		for (Iterator<Cell> cellIterator = row.iterator(); cellIterator.hasNext();) {

			Cell cell = cellIterator.next();

			list.add(getReaderConfig().getColumnHandler().handle(cell));

		}
		if (list.isEmpty() || list.stream().allMatch(column -> column.getValue() == null)) {
			allCellNull = true;
		} else {
			record = new RowRecord(this.getReaderConfig().getInstitute(), this.getReaderConfig().getProduct(), this.getReaderConfig().getConfigType().name());
			for (ColumnRecord columnRecord : list) {
				record.addColumnRecord(columnRecord);
			}
		}
		System.out.println(
				"Row No: " + row.getRowNum() + " Columns: " + row.getPhysicalNumberOfCells() + " " + record.toString());
		if (allCellNull) {
			return null;
		} else {
			return record;
		}

		/*
		 * 
		 * try { if (ReaderUtil.headersExtractedFromDoc != null) {
		 * 
		 * RowRecord record = null;
		 * 
		 * for (Iterator<Cell> cellIterator = row.iterator(); cellIterator.hasNext();) {
		 * Cell cell = cellIterator.next(); ColumnRecord columnRecord =
		 * getReaderConfig().getColumnHandler().handle(cell); if (columnRecord != null)
		 * { if (record == null) { record = new
		 * RowRecord(getReaderConfig().getInstitute(), getReaderConfig().getProduct(),
		 * getReaderConfig().getConfigType().toString()); }
		 * record.addColumnRecord(columnRecord); } }; return record; } else { RowRecord
		 * record = null;
		 * 
		 * for (Iterator<Cell> cellIterator = row.iterator(); cellIterator.hasNext();) {
		 * 
		 * Cell cell = cellIterator.next(); ColumnRecord columnRecord =
		 * getReaderConfig().getColumnHandler().handle(cell); if (columnRecord != null)
		 * { if (record == null) { record = new
		 * RowRecord(getReaderConfig().getInstitute(), getReaderConfig().getProduct(),
		 * getReaderConfig().getConfigType().toString()); }
		 * 
		 * record.addColumnRecord(columnRecord); }
		 * 
		 * } return record; } } catch (Exception e) {
		 * getReaderConfig().getExceptionHandler().handleException(e, row); } return
		 * null;
		 */
	}

	public ExcelReaderConfig getReaderConfig() {
		return (ExcelReaderConfig) super.getReaderConfig();
	}

}
