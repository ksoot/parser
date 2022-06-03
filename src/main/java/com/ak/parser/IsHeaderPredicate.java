package com.ak.parser;

import java.util.function.Predicate;

public class IsHeaderPredicate implements Predicate<RowRecord> {

	public IsHeaderPredicate() {
	}

	@Override
	public boolean test(RowRecord record) {
		int totalHeaderConfigured = ReaderUtil.totalColumnsInHeader(record.getInstitute() , record.getProduct());
		int totalHeaderScanned = 0;

		for (ColumnRecord col : record.getColumnRecords()) {
			if (col != null) {
				if (col.getValue() instanceof String && FileMetadataHolder.getInstance().headers()
						.get(record.getInstitute() + "-" + record.getProduct()).contains(col.getValue())) {
					totalHeaderScanned++;
				}
			}
		}
		return totalHeaderConfigured == totalHeaderScanned;
	}

}
