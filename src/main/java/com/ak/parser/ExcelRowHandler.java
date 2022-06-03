package com.ak.parser;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelRowHandler extends AbstractReaderHandler<RowRecord, Row> {

	public ExcelRowHandler() {
	}

	@Override
	public RowRecord handle(Row row) {

		try {
			if (ReaderUtil.headersExtractedFromDoc != null) {

				 RowRecord record = null;

				for (Iterator<Cell> cellIterator = row.iterator(); cellIterator.hasNext();) {
					Cell cell = cellIterator.next();
					ColumnRecord columnRecord = getReaderConfig().getColumnHandler().handle(cell);
					if (columnRecord != null) {
						if (record == null) {
							record = new RowRecord(getReaderConfig().getInstitute(), getReaderConfig().getProduct(), getReaderConfig().getConfigType().toString());
						}
						record.addColumnRecord(columnRecord);
					}
				};
				return record;
			} else {
				RowRecord record = null;

				for (Iterator<Cell> cellIterator = row.iterator(); cellIterator.hasNext();) {

					Cell cell = cellIterator.next();
					ColumnRecord columnRecord = getReaderConfig().getColumnHandler().handle(cell);
					if (columnRecord != null) {
						if (record == null) {
							record = new RowRecord(getReaderConfig().getInstitute(), getReaderConfig().getProduct(), getReaderConfig().getConfigType().toString());
						}

						record.addColumnRecord(columnRecord);
					}

				}
				return record;
			}
		} catch (Exception e) {
			getReaderConfig().getExceptionHandler().handleException(e, row);
		}
		return null;
	}

	public ExcelReaderConfig getReaderConfig() {
		return (ExcelReaderConfig) super.getReaderConfig();
	}

}
