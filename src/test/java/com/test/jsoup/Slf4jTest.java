package com.test.jsoup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {

    static final Logger logger  =  LoggerFactory.getLogger(Slf4jTest.class );

    public static void main(String[] args) {
    	//通用日志
		logger.info("INFO");
		logger.error("ERROR");
		logger.debug("ERROR");
		logger.warn("Warn");
		
		//输出独立日志
		Logger sync = LoggerFactory.getLogger("log.sync");
		sync.error(" EURO");
		
	}
}
