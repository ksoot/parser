package com.ak.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheetHandlerModified extends AbstractReaderHandler<SheetRecord, Sheet> {

	protected Map<Integer, String> headersExtractedFromDoc;
	Predicate<RowRecord> isHeaderPredicate;
	Predicate<RowRecord> stopProcessingPredicate;
	Predicate<RowRecord> skipRecordPredicate;

	public ExcelSheetHandlerModified(Predicate isHeaderPredicate, Predicate stopProcessingPredicate,
			Predicate skipRecordPredicate) {
		this.isHeaderPredicate = isHeaderPredicate;
		this.stopProcessingPredicate = stopProcessingPredicate;
		this.skipRecordPredicate = skipRecordPredicate;
	}

	@Override
	public SheetRecord handle(Sheet sheet) {
		boolean isRecord = false;
		boolean isHeader = false;
		headersExtractedFromDoc = new HashMap<>();
		SheetRecord records = new SheetRecord();
		int totalRecords = 0;
		Stats stats = new Stats();
		stats.setSheetName(sheet.getSheetName());
		int currentRow = sheet.getFirstRowNum();
		List<Integer> b = new ArrayList<Integer>();
		System.out.println("first row: "+sheet.getFirstRowNum());
		System.out.println("last row: "+sheet.getLastRowNum());
		//System.out.println(Math.subtractExact(sheet.getLastRowNum(), sheet.getFirstRowNum()));
		System.out.println("physical row: "+sheet.getPhysicalNumberOfRows());
		System.out.println("getDefaultColumnWidth ; " + sheet.getDefaultColumnWidth());

		try {
			for (Iterator<Row> rowIterator = sheet.iterator(); rowIterator.hasNext();) {

				stats.incrementTotalRows();
				Row row = rowIterator.next();

				while (!(row.getRowNum() == currentRow) && !(row.getRowNum() - currentRow == 1)) {
					currentRow = currentRow + 1;
					if(!(currentRow == row.getRowNum())) {
						b.add(currentRow);
					}
				}

				currentRow = row.getRowNum();

				// System.out.println(row.getPhysicalNumberOfCells());
				RowRecord record = getReaderConfig().getRowHandler().handle(row);

				if (record == null) {
					b.add(currentRow);
					continue;
				}

				if (record != null) {
					if (isRecord == false) {
						isHeader = isHeaderPredicate.test(record);

						if (isHeader == true) {
							processHeader(row);
							isRecord = true;
						}
					} else if (isRecord == true) {
						if (record == null) {
							stats.incrementTotalBlankRowsBetweenHeaderAndFooter();
							continue;
						}
						stats.incrementTotalRowsBetweenHeaderAndFooter();
						try {
							totalRecords = totalRecords + 1;
							if (stopProcessingPredicate.test(record)) {
								System.out.println("Existing processing row because we meet condition at row no. : "
										+ row.getRowNum() + ", and row data :" + record.getColumnRecords().toString());
								break;
							} else if (!skipRecordPredicate.test(record)) {
								stats.incrementTotalRowsProcessed();
								records.addRowRecord(record);
								// System.out.println(row.getRowNum() + " " +
								// record.getColumnRecords().toString());
							} else {
								stats.incrementTotalRowsSkipped();
								stats.setSkippedRow(row.getRowNum());
								System.out.println("skipped the record at row no. : -> " + row.getRowNum()
										+ ", and row data: " + record.getColumnRecords().toString());
							}

						} catch (Exception e) {
							stats.incrementTotalRowsFailed();
							stats.setFailedRow(row.getRowNum());
							System.err.println(
									"Failed to process record in sheet -> " + sheet.getSheetName() + " at row no. "
											+ row.getRowNum() + "with Record -> " + records.getRowRecords().toString());
							getReaderConfig().getExceptionHandler().handleException(e, sheet);

						}

					}
				}

			}
		} finally {
			//System.out.println(b.toString());
			stats.setBlankRows(b);
			records.setStats(stats);
			headersExtractedFromDoc.clear();
		}
		return records;
	}

	private void processHeader(Row row) {
		row.forEach(cell -> {
			if (!CellType.BLANK.equals(cell.getCellType())) {
				headersExtractedFromDoc.put(cell.getColumnIndex(), cell.getStringCellValue());
			} else {
				// System.out.println(cell.getColumnIndex());
			}

		});
		ReaderUtil.setHeaderIdentified(true);
		ReaderUtil.populateHeaderMap(headersExtractedFromDoc);
	}

	public ExcelReaderConfig getReaderConfig() {
		return (ExcelReaderConfig) super.getReaderConfig();
	}
}
