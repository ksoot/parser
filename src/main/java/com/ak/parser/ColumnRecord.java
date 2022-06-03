package com.ak.parser;

public final class ColumnRecord<T> {

	private final int columnIndex;
	private final String columnName;
	private final T value;
	
	
	public ColumnRecord(int columnIndex, String columnName, T value) {
		this.columnIndex = columnIndex;
		this.columnName = columnName;
		this.value = value;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public T getValue() {
		return value;
	}
	
	public String getColumnName() {
		return columnName;
	}

	@Override
	public String toString() {
		return "ColumnRecord [columnIndex=" + columnIndex + ", columnName=" + columnName + ", value=" + value + "]";
	}
	
	
	
	
}
