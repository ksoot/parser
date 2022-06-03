package com.ak.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RowRecord {

	private Institute institute;
	private Product product;
	private List<ColumnRecord> columnRecords;
	private int totalColumnsInRow;
	private String source;

	public RowRecord(Institute institute, Product product, String source) {
		this.institute = institute;
		this.product = product;
		this.source = source;
		columnRecords = new ArrayList<ColumnRecord>();
	}

	public List<ColumnRecord> getColumnRecords() {
		return Collections.unmodifiableList(columnRecords);
	}

	public <T> void addColumnRecord(ColumnRecord<T> record) {
		columnRecords.add(record);
		totalColumnsInRow = totalColumnsInRow + 1;
	}

	public Institute getInstitute() {
		return institute;
	}

	public Product getProduct() {
		return product;
	}
	
	public int getTotalColumnsInRow() {
		return totalColumnsInRow;
	}

	@Override
	public String toString() {
		return "RowRecord [institute=" + institute + ", product=" + product + ", columnRecords=" + columnRecords
				+ ", totalColumnsInRow=" + totalColumnsInRow + ", source=" + source + "]";
	}

	
}
