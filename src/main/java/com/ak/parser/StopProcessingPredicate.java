package com.ak.parser;

import java.util.function.Predicate;

public class StopProcessingPredicate implements Predicate<RowRecord> {


	
	public StopProcessingPredicate() {
	}
	
	
	@Override
	public boolean test(RowRecord record) {
		return record.getColumnRecords().stream().anyMatch(column -> FileMetadataHolder.getInstance().footers()
				.get(record.getInstitute() +"-"+record.getProduct()).contains(column.getValue().toString()));
	}

}
