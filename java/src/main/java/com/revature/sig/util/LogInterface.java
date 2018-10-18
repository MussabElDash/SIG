package com.revature.sig.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.*;

public interface LogInterface {

	public final static Logger logger = LogManager.getRootLogger();

	default public void logStackTrace(Throwable e) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		logger.error(writer.toString());
	}
}
