package com.ak.parser;

import org.apache.poi.ss.usermodel.Sheet;

public interface I_SheetHandler {

	I_RowHandler sheetHandler(ReaderHandler<SheetRecord, Sheet> handler);
}
