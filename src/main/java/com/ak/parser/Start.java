package com.ak.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Start {

	public static void main(String[] args) throws IOException {

	//	System.out.println("----HEADERS----");
		for (Entry<String, List<String>> entry : FileMetadataHolder.getInstance().headers().entrySet()) {
	//		System.out.println(entry.getKey());
		//	System.out.println(entry.getValue().toString());
		}

		//System.out.println("----FOOTERS----");
		for (Entry<String, List<String>> entry : FileMetadataHolder.getInstance().footers().entrySet()) {
			//System.out.println(entry.getKey());
			//System.out.println(entry.getValue().toString());
		}

		//System.out.println("----NARRATION----");
		for (Entry<String, String> entry : FileMetadataHolder.getInstance().narration().entrySet()) {
			//System.out.println(entry.getKey());
			//System.out.println(entry.getValue().toString());
		}

		// populateICICI_File("/ICICI-2020-2021.xls");
		// System.out.println("(((((((((((((((((((((((((@@@@@@@@@@@@@)))))))))))))))))))))))))");
		//populateHDFCBean("/HDFC-2017-2018.xls");
		populateZeta_File("/Zeta-statement.xls");
		//System.out.println("(((((((((((((((((((((((((@@@@@@@@@@@@@)))))))))))))))))))))))))");
	}

	private static void populateICICI_File(String path) throws IOException {

		ReaderType readerType = Utils.readerType(path);

		ReaderHandler<ColumnRecord, Cell> colHandler = new ExcelColumnHandler(new SimpleColumnParser());
		ReaderHandler<RowRecord, Row> rowHandler = new ExcelRowHandler();
		ReaderHandler<SheetRecord, Sheet> sheetHandler = new ExcelSheetHandler(new IsHeaderPredicate(),
				new StopProcessingPredicate(), new SkipRecordPredicate());

		ReaderConfig config = Configuration.getConfig(readerType).institute(Institute.ICICI).product(Product.SAVING)
				.sheetHandler(sheetHandler).rowHandler(rowHandler).columnHandler(colHandler)
				.exceptionHandler(new ExcelExceptionHandler()).build();

		Reader reader = ReaderFactory.getInstance(readerType);

		// Reader<List<SheetRecord>> reader = new ExcelReader();
		reader.configure(config);

		List<SheetRecord> sheetRecords = reader.read(path);
		for (SheetRecord sheetRecord : sheetRecords) {
			System.out.println(sheetRecord.getStats().toString());
		}

		for (SheetRecord records : sheetRecords) {
			System.out.println(records.getRowRecords().size());
			records.getRowRecords().stream()
					.forEach(record -> record.getColumnRecords().stream()
							.filter(value -> value.getColumnName().equalsIgnoreCase(FileMetadataHolder.getInstance().narration().get(Institute.ICICI+"-"+Product.SAVING)))
							.forEach(cellRecord -> RemarkEnricher.transforRemarks(cellRecord)));

		}

	}

	private static void populateHDFCBean(String path) throws IOException {

		ReaderType readerType = Utils.readerType(path);

		ReaderHandler<ColumnRecord, Cell> colHandler = new ExcelColumnHandler(new SimpleColumnParser());
		ReaderHandler<RowRecord, Row> rowHandler = new ExcelRowHandler();
		ReaderHandler<SheetRecord, Sheet> sheetHandler = new ExcelSheetHandler(new IsHeaderPredicate(),
				new StopProcessingHDFCPredicate(), new SkipRecordHDFCPredicate());

		ReaderConfig config = Configuration.getConfig(readerType).institute(Institute.HDFC).product(Product.SAVING)
				.sheetHandler(sheetHandler).rowHandler(rowHandler).columnHandler(colHandler)
				.exceptionHandler(new ExcelExceptionHandler()).build();

		Reader reader = ReaderFactory.getInstance(readerType);

		// Reader<List<SheetRecord>> reader = new ExcelReader();
		reader.configure(config);

		List<SheetRecord> sheetRecords = reader.read(path);
		for (SheetRecord sheetRecord : sheetRecords) {
			System.out.println(sheetRecord.getStats().toString());
		}

	}

	private static void populateZeta_File(String path) throws IOException {

		ReaderType readerType = Utils.readerType(path);

		ReaderHandler<ColumnRecord, Cell> colHandler = new ExcelColumnHandler(new SimpleColumnParser());
		ReaderHandler<RowRecord, Row> rowHandler = new ExcelRowHandlerModified();
		ReaderHandler<SheetRecord, Sheet> sheetHandler = new ExcelSheetHandlerModified(new IsHeaderPredicate(),
				new StopProcessingPredicate(), new SkipRecordPredicate());

		ReaderConfig config = Configuration.getConfig(readerType).institute(Institute.ZETA).product(Product.PREPAID)
				.sheetHandler(sheetHandler).rowHandler(rowHandler).columnHandler(colHandler)
				.exceptionHandler(new ExcelExceptionHandler()).build();

		Reader reader = ReaderFactory.getInstance(readerType);

		// Reader<List<SheetRecord>> reader = new ExcelReader();
		reader.configure(config);

		List<SheetRecord> sheetRecords = reader.read(path);
		for (SheetRecord sheetRecord : sheetRecords) {
			System.out.println(sheetRecord.getStats().toString());
		}
		
	}

	
}
