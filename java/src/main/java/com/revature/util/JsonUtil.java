package com.revature.util;

import java.io.IOException;

import static com.revature.util.LogInterface.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
	
	private static ObjectMapper objectMapper;

	public static String convertJavaToJson(Object obj) {
		String jsonString = "";
		try {
			jsonString=objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("Exception occured in converting object to JSON: " + obj.toString());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
}