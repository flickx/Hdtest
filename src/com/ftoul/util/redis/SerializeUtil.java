package com.ftoul.util.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeUtil {
	public static byte[] serialize(Object object) {
		String resStr = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			resStr = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resStr.getBytes();
	}

	public static Object unserialize(byte[] bytes,Class c) {
		ObjectMapper mapper = new ObjectMapper();
	    Object vo;
		try {
			vo = mapper.readValue(new String(bytes), c);
			return vo;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	    return null;
	}
}
