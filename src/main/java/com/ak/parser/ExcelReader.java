package com.ak.parser;

import static  com.ak.parser.Utils.getExtention;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader implements Reader {

	private ExcelReaderConfig config;

	public ExcelReader() {
	}

	public final List<SheetRecord> read(String path) throws IOException {
		List<SheetRecord> workBookRecords = new ArrayList<SheetRecord>();

		InputStream fis = null;
		try {
			fis = getClass().getResourceAsStream(path);
			Extention ext = getExtention(path);
			Workbook workbook = ReaderUtil.getWorkbook(ext, fis);
			workbook.sheetIterator().forEachRemaining(sheet -> workBookRecords.add(config.getSheetHandler().handle(sheet)));
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fis.close();
		}
		return workBookRecords;
	}

	@Override
	public void configure(ReaderConfig config) {
		this.config = (ExcelReaderConfig) config;

	}

	@Override
	public ReaderConfig resetConfig(ReaderConfig newConfig) {
		final ReaderConfig oldConfig = this.config;
		this.config = (ExcelReaderConfig) newConfig;
		return oldConfig;
	}

	@Override
	public ReaderConfig getConfig() {
		return config;
	}
}
