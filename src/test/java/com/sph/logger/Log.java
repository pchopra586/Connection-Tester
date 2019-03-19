package com.sph.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {

	static Logger Log = Logger.getLogger(Log.class.getName());

	public static void INFO(String Message) {
		DOMConfigurator.configure("log4j.xml");
		Log.info(Message);
	}

	public static void ERROR(String Message) {
		DOMConfigurator.configure("log4j.xml");
		Log.error(Message);
	}

}
