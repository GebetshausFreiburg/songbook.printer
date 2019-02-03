/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Dr. Matthias Wegner
 * @version 0.1
 * @since 01.04.2018
 * 
 */
package org.openskies.songbook.printer.webserver;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.eclipse.jetty.util.log.AbstractLogger;
import org.eclipse.jetty.util.log.Logger;

/**
 * Logger-Bridge to log jetty-logs to log4j in circlead-application
 * 
 * User: Robert Franz Date: 2015-08-24 Time: 20:35.
 */
public class Jetty2Log4j2Bridge extends AbstractLogger {
	
	/** The logger. */
	private org.apache.logging.log4j.Logger logger;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new jetty 2 log 4 j 2 bridge.
	 *
	 * @param name the name
	 */
	public Jetty2Log4j2Bridge(String name) {
		this.name = name;
		logger = LogManager.getLogger(name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.AbstractLogger#newLogger(java.lang.String)
	 */
	@Override
	protected Logger newLogger(String fullname) {
		return new Jetty2Log4j2Bridge(fullname);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#warn(java.lang.String, java.lang.Object[])
	 */
	public void warn(String msg, Object... args) {
		logger.warn(msg, args);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#warn(java.lang.Throwable)
	 */
	public void warn(Throwable thrown) {
		logger.catching(Level.WARN, thrown);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#warn(java.lang.String, java.lang.Throwable)
	 */
	public void warn(String msg, Throwable thrown) {
		logger.warn(msg, thrown);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#info(java.lang.String, java.lang.Object[])
	 */
	public void info(String msg, Object... args) {
		logger.info(msg, args);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#info(java.lang.Throwable)
	 */
	public void info(Throwable thrown) {
		logger.catching(Level.INFO, thrown);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#info(java.lang.String, java.lang.Throwable)
	 */
	public void info(String msg, Throwable thrown) {
		logger.info(msg, thrown);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#setDebugEnabled(boolean)
	 */
	public void setDebugEnabled(boolean enabled) {
		warn("setDebugEnabled not implemented", null, null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#debug(java.lang.String, java.lang.Object[])
	 */
	public void debug(String msg, Object... args) {
		logger.debug(msg, args);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#debug(java.lang.Throwable)
	 */
	public void debug(Throwable thrown) {
		logger.catching(Level.DEBUG, thrown);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#debug(java.lang.String, java.lang.Throwable)
	 */
	public void debug(String msg, Throwable thrown) {
		logger.debug(msg, thrown);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jetty.util.log.Logger#ignore(java.lang.Throwable)
	 */
	public void ignore(Throwable ignored) {
		logger.catching(Level.TRACE, ignored);
	}
}
