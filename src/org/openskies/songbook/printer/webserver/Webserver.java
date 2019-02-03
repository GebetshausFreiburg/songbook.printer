/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Dr. Matthias Wegner
 * @version 0.1
 * @since 01.04.2018
 * 
 */
package org.openskies.songbook.printer.webserver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.resource.Resource;

/**
 * The Class Webserver to run a embedded jetty-webserver.
 *
 * @author Matthias Wegner
 */
public class Webserver extends Server {

	/** The Constant logger. */
	final static Logger LOGGER = LogManager.getLogger(Webserver.class);

	/** The port. */
	final int PORT = 8080;

	public static void main(String[] args) {
		LOGGER.info("Start Webserver through Launcher");
		Webserver server = new Webserver();
		server.run();
	}
	
	/**
	 * Instantiates a new webserver.
	 */
	public Webserver() {
	
		Log.setLog(new Jetty2Log4j2Bridge("Webserver"));
		
		LOGGER.info("Initialize Webserver");

		ServerConnector connector = new ServerConnector(this);
		connector.setPort(PORT);
		this.setConnectors(new Connector[] { connector });

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		String webDirectory = "web";
		String landingpage = "index.html";

		context.setContextPath("/");
		context.setBaseResource(Resource.newResource(new File(webDirectory)));
		context.setWelcomeFiles(new String[] { landingpage });

		ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
		holderPwd.setInitParameter("dirAllowed", "true");
		context.addServlet(holderPwd, "/");

		this.setHandler(context);
	}

	/**
	 * Open in browser.
	 */
	public void openInBrowser() {
		try {
			String url = "http://localhost:"+PORT+"/";
			URI openIt = new URL(url).toURI();
			java.awt.Desktop.getDesktop().browse(openIt);
		} catch (MalformedURLException e) {
			LOGGER.error(e);
		} catch (URISyntaxException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		LOGGER.info("Stop Webserver");

		try {
			this.stop();
		} catch (Exception e) {
			LOGGER.error("Error thrown on Web-Server", e);
		}
	}

	/**
	 * Run.
	 */
	public void run() {
		LOGGER.info("Start Webserver at http://localhost:" + PORT + "/");
		try {
			this.start();
			this.join();
		} catch (InterruptedException e) {
			LOGGER.error("Error thrown on Web-Server", e);
		} catch (Exception e) {
			LOGGER.error("Error thrown on Web-Server", e);
		}
	}
}
