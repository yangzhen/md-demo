package com.md.demo.server.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;



public class DemoAction {
	
	private DemoService demoService;
	
	private static Logger logger = Logger.getLogger(DemoAction.class);

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

	public void start() throws Exception {
		System.out.println("====================================================================");
		logger.info("====================================================================");
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            try {
            	String hello = demoService.sayHello("world" + i);
            	logger.info("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
                System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }
	}

}
