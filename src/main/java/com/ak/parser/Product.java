package com.ak.parser;

public enum Product {
	
	SAVING("saving"), FIXED_DEPOSIT("fixecDeposit"), CREDIT_CARD("creditCard"), PREPAID("prePaid");

	private final String value;

	Product(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
