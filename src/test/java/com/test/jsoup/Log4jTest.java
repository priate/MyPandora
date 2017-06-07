package com.test.jsoup;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Log4jTest.class.getName());
		
		logger.setLevel(Level.INFO);
		logger.debug("This is debug ");
		logger.error("This is error");
		logger.info("This is info ");
	}
}
