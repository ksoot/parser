package com.ak.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReaderUtil {

	public static Map<Integer, String> headersExtractedFromDoc;
	private static boolean flag;

	public static void populateHeaderMap(Map<Integer, String> headerMap) {
		headersExtractedFromDoc = headerMap;
	}

	public static Workbook getWorkbook(Extention ext, InputStream fis) throws IOException {
		Workbook workbook = null;
		if (ext == Extention.XLSX) {
			workbook = new XSSFWorkbook(fis);
		} else if (ext == Extention.XLS) {
			workbook = new HSSFWorkbook(fis);
		}
		return workbook;

	}

	public static int totalColumnsInHeader(Institute intstitute, Product product) {
		return FileMetadataHolder.getInstance().headers().get(intstitute + "-" + product).size();
	}

	
	public static String headerHavingTransDetails(Institute intstitute, Product product) {
		return null;
	}
	
	public static boolean isHeaderIdentified() {
		return flag;
	}
	
	public static void setHeaderIdentified(boolean flag) {
		ReaderUtil.flag = flag;
	}

}
