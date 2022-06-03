package com.ak.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Stats {

	private String sheetName;
	private int totalRows;
	private int totalRowsBetweenHeaderAndFooter;
	private int totalBlankRowsBetweenHeaderAndFooter;
	private int totalRowsProcessed;
	private int totalRowsSkipped;
	private int totalRowsFailed;
	private List<Integer> blankRows = new ArrayList<Integer>();
	private List<Integer> skippedRows = new ArrayList<Integer>();
	private List<Integer> failedRows = new ArrayList<Integer>();

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void incrementTotalRows() {
		this.totalRows = totalRows + 1;
	}

	public int getTotalRowsBetweenHeaderAndFooter() {
		return totalRowsBetweenHeaderAndFooter;
	}

	public void incrementTotalRowsBetweenHeaderAndFooter() {
		this.totalRowsBetweenHeaderAndFooter = totalRowsBetweenHeaderAndFooter + 1;
	}

	public int getTotalBlankRowsBetweenHeaderAndFooter() {
		return totalBlankRowsBetweenHeaderAndFooter;
	}

	public void incrementTotalBlankRowsBetweenHeaderAndFooter() {
		this.totalBlankRowsBetweenHeaderAndFooter = totalBlankRowsBetweenHeaderAndFooter + 1;
	}

	public int getTotalRowsProcessed() {
		return totalRowsProcessed;
	}

	public void incrementTotalRowsProcessed() {
		this.totalRowsProcessed = totalRowsProcessed + 1;
	}

	public int getTotalRowsSkipped() {
		return totalRowsSkipped;
	}

	public void incrementTotalRowsSkipped() {
		this.totalRowsSkipped = totalRowsSkipped + 1;
	}

	public int getTotalRowsFailed() {
		return totalRowsFailed;
	}

	public void incrementTotalRowsFailed() {
		this.totalRowsFailed = totalRowsFailed + 1;
	}
	
	public List<Integer> getBlankRows() {
		return blankRows;
	}
	
	public void setBlankRows(List<Integer> blankRows) {
		this.blankRows = blankRows;
	}

	public List<Integer> getSkippedRows() {
		return Collections.unmodifiableList(skippedRows);
	}

	public void setSkippedRow(int rowNum) {
		this.skippedRows.add(rowNum);
	}

	public List<Integer> getFailedRows() {
		return Collections.unmodifiableList(failedRows);
	}

	public void setFailedRow(int rowNum) {
		this.failedRows.add(rowNum);
	}

	@Override
	public String toString() {
		return "Stats [sheetName=" + sheetName + ", totalRows=" + totalRows + ", totalRowsBetweenHeaderAndFooter="
				+ totalRowsBetweenHeaderAndFooter + ", totalBlankRowsBetweenHeaderAndFooter="
				+ totalBlankRowsBetweenHeaderAndFooter + ", totalRowsProcessed=" + totalRowsProcessed
				+ ", totalRowsSkipped=" + totalRowsSkipped + ", totalRowsFailed=" + totalRowsFailed + ", blankRows="
				+ blankRows.toString() + ", skippedRows=" + skippedRows.toString() + ", failedRows=" + failedRows.toString() + "]";
	}

	

}
