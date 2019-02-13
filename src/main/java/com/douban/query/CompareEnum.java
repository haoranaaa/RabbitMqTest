package com.douban.query;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 12 九月 2018
 */
public enum CompareEnum {
	lt("<"), gt(">"), le("<="), ge(">="),eq("=");

	private String formula;

	CompareEnum(String formula) {
		this.formula = formula;
	}

	public String getFormula() {
		return formula;
	}
}
