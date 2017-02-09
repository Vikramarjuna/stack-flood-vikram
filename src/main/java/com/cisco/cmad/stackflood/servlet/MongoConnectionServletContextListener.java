package com.cisco.cmad.stackflood.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cisco.cmad.stackflood.util.MongoConnection;

public class MongoConnectionServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.close();
	}

}
