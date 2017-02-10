package com.cisco.cmad.stackflood.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props = new Properties();

    public static void loadProperties(String name) {
        props.clear();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(name);
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                props.put(key, bundle.getString(key));
            }
        } catch (Exception e) {
            LOGGER.error("Exception loading properties.", e);
        }

    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static Properties getProperties(String name) {
        Properties properties = new Properties();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(name);
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                properties.put(key, (String) bundle.getObject((String) key));
            }
        } catch (Exception e) {
            LOGGER.error("Exception while reading the values from the file :: " + name, e);
        }

        return properties;
    }
    
    public static final void initLog4j() {
    	Properties props = getProperties("log4j");
    	PropertyConfigurator.configure(props);
    }
    
}
