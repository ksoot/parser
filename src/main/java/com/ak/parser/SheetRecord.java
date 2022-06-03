package com.ak.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SheetRecord {

	private List<RowRecord> rowRecords;
	private int totalRecordsInSheet;
	private Stats stats;

	public SheetRecord() {
		rowRecords = new ArrayList<RowRecord>();
	}

	public List<RowRecord> getRowRecords() {
		return Collections.unmodifiableList(rowRecords);
	}

	public void addRowRecord(RowRecord record) {
		rowRecords.add(record);
		totalRecordsInSheet = totalRecordsInSheet + 1;
	}

	public int getTotalRecordsInSheet() {
		return totalRecordsInSheet;
	}
	
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public Stats getStats() {
		return stats;
	}
}
