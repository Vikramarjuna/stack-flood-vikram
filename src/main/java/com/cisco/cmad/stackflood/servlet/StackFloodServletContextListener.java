package com.cisco.cmad.stackflood.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cmad.stackflood.util.PropertiesUtil;

public class StackFloodServletContextListener implements ServletContextListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StackFloodServletContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		String contextPath = sce.getServletContext().getContextPath();
		LOGGER.info("Starting stack-flood. Context path " + contextPath);

		try {
			LOGGER.info("Initializing log4j");
			PropertiesUtil.initLog4j();
		} catch (Exception e) {
			LOGGER.error("Exception occurred intializing log4j" + e.getMessage());
		}

		try {
			LOGGER.info("Initializing property files");
			PropertiesUtil.loadProperties("stack-flood");
		} catch (Exception e) {
			LOGGER.error("Exception occurred intializing properties" + e.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing to handle
	}

}
