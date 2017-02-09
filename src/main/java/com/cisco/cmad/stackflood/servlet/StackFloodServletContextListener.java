package com.cisco.cmad.stackflood.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cisco.cmad.stackflood.util.PropertiesUtil;

public class StackFloodServletContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		String contextPath = sce.getServletContext().getContextPath();
		System.out.println("Starting stack-flood. Context path " + contextPath);

		try {
			System.out.println("Initializing log4j");
			PropertiesUtil.initLog4j();
		} catch (Exception e) {
			System.out.println("Exception occurred intializing log4j" + e.getMessage());
		}

		try {
			System.out.println("Initializing property files");
			PropertiesUtil.loadProperties("stack-flood");
		} catch (Exception e) {
			System.out.println("Exception occurred intializing properties" + e.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing to handle
	}

}
