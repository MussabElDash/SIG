package com.revature.util;

import org.apache.log4j.Logger;

public class LoggingUtil {

private static Logger log = Logger.getLogger(LoggingUtil.class.getName());
	
	public static Logger getLogger() {
		return log;
	}
	
}
