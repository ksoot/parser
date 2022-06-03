package com.ak.parser;

public enum Institute {

	ICICI("icici"), HDFC("hdfc"), YES_BANK("yesBank"), PNB("pnb"), DCB("dcb"), JANA_BANK("janaBank"), ZETA("zeta");

	private final String value;

	Institute(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
