package com.df.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClazzStruct {

	/**
	 * Ä§Êý u2
	 */
	private String magic;
	
	private int minor;
	
	private int major;
	
	private int constantCount;
	
	/**
	 * ³£Á¿³Ø
	 */
	private List<ConstantInfo> cpInfo = new ArrayList<ConstantInfo>();
	
	private List<String> accessFlags;
	
	private String thisClazz;
	
	private String superClazz;
	
	private int interfaceCount;
	
	private Object[] interfaces;
	
	private int fieldCount;
	
	private Object[] fields;
	
	private int mehtodCount;
	
	private Object[] methods = null;
	
	private int attrCount;
	
	private Object[] attrs;

	public String getMagic() {
		return magic;
	}

	public void setMagic(String magic) {
		this.magic = magic;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getConstantCount() {
		return constantCount;
	}

	public void setConstantCount(int constantCount) {
		this.constantCount = constantCount;
	}

	public List<ConstantInfo> getCpInfo() {
		return cpInfo;
	}

	public void setCpInfo(List<ConstantInfo> cpInfo) {
		this.cpInfo = cpInfo;
	}

	public String getThisClazz() {
		return thisClazz;
	}

	public void setThisClazz(String thisClazz) {
		this.thisClazz = thisClazz;
	}

	public String getSuperClazz() {
		return superClazz;
	}

	public void setSuperClazz(String superClazz) {
		this.superClazz = superClazz;
	}

	public int getInterfaceCount() {
		return interfaceCount;
	}

	public void setInterfaceCount(int interfaceCount) {
		this.interfaceCount = interfaceCount;
	}

	public Object[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Object[] interfaces) {
		this.interfaces = interfaces;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public Object[] getFields() {
		return fields;
	}

	public void setFields(Object[] fields) {
		this.fields = fields;
	}

	public int getMehtodCount() {
		return mehtodCount;
	}

	public void setMehtodCount(int mehtodCount) {
		this.mehtodCount = mehtodCount;
	}

	public Object[] getMethods() {
		return methods;
	}

	public void setMethods(Object[] methods) {
		this.methods = methods;
	}

	public int getAttrCount() {
		return attrCount;
	}

	public void setAttrCount(int attrCount) {
		this.attrCount = attrCount;
	}

	public Object[] getAttrs() {
		return attrs;
	}

	public void setAttrs(Object[] attrs) {
		this.attrs = attrs;
	}

	public List<String> getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(List<String> accessFlags) {
		this.accessFlags = accessFlags;
	}

	public void constantpoolSay(){
		System.out.println("Constant pool:");
		for(int i=0;i<cpInfo.size();i++){
			ConstantInfo info = cpInfo.get(i);
			StringBuilder sb = new StringBuilder("#");
			sb.append(i+1);
			sb.append(" = " +info.getType()+"          ");
			if(null!=info.getRefList()){
				for(String s:info.getRefList()){
					sb.append("#"+s+".");
				}
				sb.append("          //");
				sb.append(info.getValue());
			}else{
				sb.append(info.getValue());
			}
			System.out.println(sb.toString());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClazzStruct [magic=");
		builder.append(magic);
		builder.append(", minor=");
		builder.append(minor);
		builder.append(", major=");
		builder.append(major);
//		builder.append(", constantCount=");
//		builder.append(constantCount);
		builder.append(", cpInfo=");
		builder.append(cpInfo);
		builder.append(", accessFlags=");
		builder.append(accessFlags);
		builder.append(", thisClazz=");
		builder.append(thisClazz);
		builder.append(", superClazz=");
		builder.append(superClazz);
		builder.append(", interfaceCount=");
		builder.append(interfaceCount);
		builder.append(", interfaces=");
		builder.append(Arrays.toString(interfaces));
		builder.append(", fieldCount=");
		builder.append(fieldCount);
		builder.append(", fields=");
		builder.append(Arrays.toString(fields));
		builder.append(", mehtodCount=");
		builder.append(mehtodCount);
		builder.append(", methods=");
		builder.append(Arrays.toString(methods));
		builder.append(", attrCount=");
		builder.append(attrCount);
		builder.append(", attrs=");
		builder.append(Arrays.toString(attrs));
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
