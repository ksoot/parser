package com.ak.parser;

import java.util.function.Predicate;

public class SkipRecordPredicate implements Predicate<RowRecord> {

	
	public SkipRecordPredicate() {
	}
	
	

	@Override
	public boolean test(RowRecord record) {
		return record.getColumnRecords().isEmpty()
				|| record.getColumnRecords().size() != ReaderUtil.totalColumnsInHeader(record.getInstitute() ,record.getProduct());
	}

}
