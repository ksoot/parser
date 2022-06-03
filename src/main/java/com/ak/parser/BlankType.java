package com.ak.parser;

import java.lang.reflect.Type;

import org.apache.poi.ss.usermodel.CellType;

public class BlankType implements Type{

	@Override
	public String getTypeName() {
		return CellType.BLANK.toString();
	}
}
