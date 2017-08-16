package com.wangxiandeng.classreader.constantinfo;

import com.wangxiandeng.classreader.ConstantInfo;
import com.wangxiandeng.classreader.basictype.U8;

import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by wangxiandeng on 2017/1/25.
 */
public class ConstantLong extends ConstantInfo {

    private static ByteBuffer buffer = ByteBuffer.allocate(8);      
	
    @Override
    public void read(InputStream inputStream) {
    	value = String.valueOf(bytes2Long(U8.read(inputStream)));
    }
    
    public static long bytes2Long(byte[] bytes) {  
	       buffer.put(bytes, 0, bytes.length);  
	       buffer.flip();//need flip   
	       return buffer.getLong();  
	}  
}
