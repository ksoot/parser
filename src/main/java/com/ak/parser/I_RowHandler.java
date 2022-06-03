package com.ak.parser;

import org.apache.poi.ss.usermodel.Row;

public interface I_RowHandler {

	I_ColumnHandler rowHandler(ReaderHandler<RowRecord, Row> handler);
	
}
