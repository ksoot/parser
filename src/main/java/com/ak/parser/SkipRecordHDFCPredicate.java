package com.ak.parser;

import java.util.function.Predicate;

public class SkipRecordHDFCPredicate implements Predicate<RowRecord> {

	public SkipRecordHDFCPredicate() {
	}

	@Override
	public boolean test(RowRecord record) {
		return record.getColumnRecords().isEmpty()
				|| record.getColumnRecords().size() != ReaderUtil
						.totalColumnsInHeader(record.getInstitute() , record.getProduct())
				|| record.getColumnRecords().stream().allMatch(cell -> cell.getValue().toString().startsWith("***"));
	}

}
