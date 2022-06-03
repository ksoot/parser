package com.ak.parser;

import org.apache.poi.ss.usermodel.Cell;

public interface I_ColumnHandler {

	I_ExceptionHandler columnHandler(ReaderHandler<ColumnRecord, Cell> handler);
}
