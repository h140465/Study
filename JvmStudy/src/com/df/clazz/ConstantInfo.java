package com.df.clazz;

import java.util.List;

public class ConstantInfo {

	private String type;
	
	private Object value;
	
	private List<String> refList;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<String> getRefList() {
		return refList;
	}

	public void setRefList(List<String> refList) {
		this.refList = refList;
	}

	@Override
	public String toString() {
		return "ConstantInfo [type=" + type + ", value=" + value + ", refList="
				+ refList + "]";
	}
	
	
}
