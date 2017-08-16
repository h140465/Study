package com.df.clazz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;


public class ClazzAnalysis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final char[] hexCode = "0123456789ABCDEF".toCharArray();  
	
	
    private static ByteBuffer buffer = ByteBuffer.allocate(8);      

    private static ClazzStruct clazzStruct = new ClazzStruct();
	
	public static void main(String[] args) throws IOException {
		File file = new File("D:/JavaTest/JavaSampleApped.class");
		FileInputStream input = new FileInputStream(file); 
		byte[] magic = new byte[4];
		input.read(magic);
		
		clazzStruct.setMagic(printHexBinary(magic));
		
		byte[] minorVer = new  byte[2];
		input.read(minorVer);
		clazzStruct.setMinor(bytes2Int(minorVer));

		byte[] majorVer = new  byte[2];
		input.read(majorVer);
		clazzStruct.setMajor(bytes2Int(majorVer));
		
		byte[] consCount = new  byte[2];
		input.read(consCount);
		clazzStruct.setConstantCount(bytes2Int(consCount));

		poolAnalysis(input,clazzStruct.getConstantCount());
//		
		accessFlagsAnalysis(input);
		
//		byte[] clazzName = new byte[2];
//		input.read(clazzName);
//		System.out.println(""+clazzStruct.getCpInfo().get(bytes2Int(clazzName)-1).getValue());
//		
//		byte[] superName = new byte[2];
//		input.read(superName);
//		System.out.println(""+clazzStruct.getCpInfo().get(bytes2Int(superName)-1).getValue());
//		
//		interfaceAnalysis(input);

//		System.out.println(clazzStruct);
		clazzStruct.constantpoolSay();
//		System.out.println(bytes2Int(minorVer,0,2));
//		System.out.println(bytes2Int(majorVer,0,2));
//		System.out.println(bytes2Int(consCount,0,2));
//		System.out.println(bytes2Int(conTag,0,1));
	}

	
	
	
	 public static String printHexBinary(byte[] data) {  
	        StringBuilder r = new StringBuilder(data.length * 2);  
	        for (byte b : data) {  
	            r.append(hexCode[(b >> 4) & 0xF]);  
	            r.append(hexCode[(b & 0xF)]);  
	        }  
	        return r.toString();  
	    }  
	 
	 public static void poolAnalysis(FileInputStream input,int length) throws IOException {  
		 
		List<ConstantInfo> cpList = clazzStruct.getCpInfo();
		for(int i =1;i<length;i++){
			byte[] conTag = new byte[1];
			input.read(conTag);
			
			ConstantInfo cinfo = new ConstantInfo();
			switch(bytes2Int(conTag)){
				case 1:
				{
					byte[] u2 = new  byte[2];
					input.read(u2);
					int len = bytes2Int(u2);
					byte[] bts = new byte[len];
					input.read(bts);
//						System.out.println("utf-8£º"+ StringEscapeUtils.escapeJava(new String(bts)));
					cinfo.setType("utf-8");
					cinfo.setValue(StringEscapeUtils.escapeJava(new String(bts)));
					break;
				}
				case 3:
				{
					byte[] u4 = new  byte[4];
					input.read(u4);
//						System.out.println("Integer_info:"+bytes2Int(u4,0,4));
					cinfo.setType("Integer_info");
					cinfo.setValue(bytes2Int(u4));
					break;
				}
				case 4:
				{
					byte[] u4 = new  byte[4];
					input.read(u4);
//					System.out.println("Float_info:"+bytes2Float(u4));
					cinfo.setType("Float_info");
					cinfo.setValue(bytes2Float(u4));
					break;
				}
				case 5:
				{
					byte[] u8 = new  byte[8];
					input.read(u8);
//					System.out.println("Long_info:"+bytes2Long(u4));
					cinfo.setType("Long_info");
					cinfo.setValue(bytes2Long(u8));
					i++;
					break;
				}
				case 6:
				{
					byte[] u4 = new  byte[4];
					input.read(u4);
//					System.out.println("Double_info:"+bytes2Double(u4));
					cinfo.setType("Double_info");
					cinfo.setValue(bytes2Double(u4));
					break;
				}
				case 7:
				{
					byte[] u2 = new  byte[2];
					input.read(u2);
//					System.out.println("Class_info:"+bytes2Int(u2,0,2));
					cinfo.setType("Class_info");
//					cinfo.setValue(bytes2Double(u4));
					
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(u2));
					cinfo.setRefList(list);
					break;
				}
				case 8:
				{
					byte[] u2 = new  byte[2];
					input.read(u2);
//					System.out.println("String_info:"+bytes2Int(u2,0,2));
					cinfo.setType("String_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(u2));
					cinfo.setRefList(list);

					break;
				}
				case 9:
				{
					byte[] index1 = new  byte[2];
					input.read(index1);
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("FieldRef_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(index1));
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
//					System.out.println("FieldRef_info:"+bytes2Int(index1,0,2)+"£¬"+bytes2Int(index2,0,2));
					break;
				}
				case 10:
				{
					byte[] index1 = new  byte[2];
					input.read(index1);
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("MethodRef_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(index1));
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
//					System.out.println("MethodRef_info:"+bytes2Int(index1,0,2)+"£¬"+bytes2Int(index2,0,2));
					break;
				}
				case 11:
				{
					byte[] index1 = new  byte[2];
					input.read(index1);
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("InterfaceMethodRef_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(index1));
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
//					System.out.println("InterfaceMethodRef_info:"+bytes2Int(index1,0,2)+"£¬"+bytes2Int(index2,0,2));
					break;
				}
				case 12:
				{
					byte[] index1 = new  byte[2];
					input.read(index1);
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("NameAndType_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(index1));
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
					
//					System.out.println("NameAndType_info:"+bytes2Int(index1,0,2)+"£¬"+bytes2Int(index2,0,2));
					break;
				}
				case 15:
				{
					byte[] u1 = new  byte[1];
					input.read(u1);
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("Method_Handle_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(u1));
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
//					System.out.println("Method_Handle_info:"+bytes2Int(u1,0,1)+"£¬"+bytes2Int(index2,0,2));
					break;
				}
				case 16:
				{
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("Method_Type_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
//					System.out.println("Method_Type_info:"+bytes2Int(index2,0,2));
					break;
				}
				case 18:
				{
					byte[] u2 = new  byte[2];
					input.read(u2);
					byte[] index2 = new  byte[2];
					input.read(index2);
					
					cinfo.setType("Invoke_Dynamic_info");
					List<String> list = new ArrayList<String>();
					list.add(""+bytes2Int(u2));
					list.add(""+bytes2Int(index2));
					cinfo.setRefList(list);
//					System.out.println("Invoke_Dynamic_info:"+bytes2Int(u2,0,2)+"£¬"+bytes2Int(index2,0,2));
					break;
				}
			}
			cpList.add(cinfo);
		}	
		
		for(ConstantInfo info :cpList){
//			if(null!=info.getRefList()){
//				StringBuilder sb = new StringBuilder();
//				for(String s:info.getRefList()){
//					sb.append(cpList.get(Integer.valueOf(s)-1).getValue());
//				}
//				info.setValue(sb.toString());
//			}
			setValue(cpList, info);
		}
	 }  
	 
	 public static void setValue(List<ConstantInfo> cpList,ConstantInfo info){
		 if(null==info.getValue()&&null!=info.getRefList()){
				StringBuilder sb = new StringBuilder();
				for(String s:info.getRefList()){
					ConstantInfo cinfo = cpList.get(Integer.valueOf(s)-1);
					if(null==cinfo.getValue()){
						setValue(cpList,cinfo);
					}
					sb.append(cinfo.getValue());
				}
				info.setValue(sb.toString());
			}
	 }
	 
	 public static void  accessFlagsAnalysis(FileInputStream input) throws IOException{
		 byte[] u2 = new byte[2];
		 input.read(u2);
		 
		 List<String> list = new ArrayList<String>();
		 String str = printHexBinary(u2);
		 
		 
		 char s = str.charAt(3);
		 if(s == '1')
			 list.add("ACC_PUBLIC");
		 
		 s = str.charAt(2);
		 if(s == '1')
			 list.add("ACC_FINAL");
		 else if(s == '2')
			 list.add("ACC_SUPER"); 
		 
		 s = str.charAt(1);
		 if(s == '2')
			 list.add("ACC_INTERFACE");
		 else if(s == '4')
			 list.add("ACC_ABSTRACT");
		 
		 s = str.charAt(0);
		 if(s == '1')
			 list.add("ACC_SYNTHETIC");
		 else if(s == '2')
			 list.add("ACC_ANNOTATION");
		 else if(s == '4')
			 list.add("ACC_ENUM");
		 
		System.out.println(list);
	 }
	 
	 public static void interfaceAnalysis(FileInputStream input) throws IOException{
		 byte[] u2 = new byte[2];
		 input.read(u2);
		 int no = bytes2Int(u2);
		 for(int i=1;i<=no;i++){
			 input.read(u2);
			 System.out.println("½Ó¿Ú:"+clazzStruct.getCpInfo().get(bytes2Int(u2)-1).getValue());
		 }
	 }
	 
	 public static int bytesToInt2(byte[] src) {  
		    int value;    
		    value = (int) ((src[0] & 0xFF)<<0xFF);
		    return value;  
	}  
	 
	 public static int bytes2Int(byte[] b, int start, int len) {
		    int sum = 0;
		    int end = start + len;
		    for (int i = start; i < end; i++) {
		        int n = ((int)b[i]) & 0xff;
		        n <<= (--len) * 8;
		        sum += n;
		    }
		    return sum;
	}
	 
	 public static int bytes2Int(byte[] b){
		 return bytes2Int(b,0,b.length);
	 }

	 public static double bytes2Double(byte[] arr) {  
	        long value = 0;  
	        for (int i = 0; i < 8; i++) {  
	            value |= ((long) (arr[i] & 0xff)) << (8 * i);  
	        }  
	        return Double.longBitsToDouble(value);  
	    }
	 
	 
	 public static double bytes2Float(byte[] arr) {  
	     return Float.intBitsToFloat(bytes2Int(arr,0,4));  
	 }
	 
	 public static long bytes2Long(byte[] bytes) {  
	       buffer.put(bytes, 0, bytes.length);  
	       buffer.flip();//need flip   
	       return buffer.getLong();  
	   }  
	 
}
