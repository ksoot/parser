package com.ak.parser;

import java.util.function.Predicate;

public class StopProcessingHDFCPredicate implements Predicate<RowRecord> {

	private int attempt = 0;

	@Override
	public boolean test(RowRecord record) {

		if (record.getColumnRecords().isEmpty()) {
			return false;
		}

		boolean allMatch = record.getColumnRecords().stream()
				.allMatch(cell -> cell.getValue().toString().startsWith("***"));

		if (allMatch) {

			if (attempt == 0) {
				attempt = 1;
				return false;
			} else {
				return true;
			}
		}
		return allMatch;
	}
	

}
