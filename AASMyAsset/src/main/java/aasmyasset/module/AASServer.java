/*******************************************************************************
 * Copyright (c) 2024 DFKI.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributor:
 * 		DFKI - Tapanta Bhanja <tapanta.bhanja@dfki.de>
	*		FESTO - Moritz Marseu <moritz.marseu@festo.com>
 ******************************************************************************/
package aasmyasset.module;

import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.eclipse.basyx.vab.protocol.http.server.BaSyxContext;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxHTTPServer;
import com.festo.aas.p4m.configuration.CommandLineOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aasmyasset.connection.ConnectedDevices;

/**
 * The central class of this AAS application. This class contains the main method which createst the AAS Model
 * and launches the server which exposes the model on the network. 
 * 
 * @author DFKI
 *
 */
public class AASServer extends BaSyxHTTPServer {
	
	private static final Logger logger = LoggerFactory.getLogger(AASServer.class);
	private static Settings configuration;
	private static AASModels models;
	private static ConnectedDevices connectors;
	
	/**
	 * Creates a new instance of this application. 
	 * 
	 * @param context	A context object which contains data needed for the server. 
	 * 
	 */
	private AASServer(BaSyxContext context) {
		super(context);
	}

	/**
	 * This main method creates all required objects and launches a server exposing this AAS Model on the network. 
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		CommandLine cmd = parseCommandLine(args);
		// Load application settings
		if(cmd.hasOption("p")) {
			configuration = new Settings(cmd.getOptionValue("p"));
		}
		
		else { 
			configuration = new Settings();
		}
		
		configuration.load();
		
		connectors = new ConnectedDevices();
		models = new AASModels();
		
		BaSyxContext context = DeviceContext.forModels(models);
		AASServer app = new AASServer(context);
		app.start();
		
		if (app.hasEnded()) {
            logger.error("AAS server failed to start. Process is terminating.");
            System.exit(1);
        } 
		else {
        	String contextPath = "/aas";
            String serverUrl = String.format("http://%s:%d%s", configuration.applicationHostname.get(), configuration.applicationPort.get(),
                    contextPath);
            logger.info("AAS '{}' is listening at: {}", configuration.aasName.get(), serverUrl);
            logger.info(
                    "You can find the API documentation at https://app.swaggerhub.com/apis/BaSyx/basyx_submodel_repository_http_rest_api/v1#/");
        }

	}
	
	/**
	 * Gets the AAS models this application is serving. 
	 * 
	 * @return The models this AAS is serving. 
	 */
	public static AASModels getModels() {
		return models;
	}
	
	/**
	 * Gets an object which contains all configured connectors to external servers. 
	 * 
	 * @return An object with all configured connectors. 
	 */
	public static ConnectedDevices getConnectors() {
		return connectors;
	}
	
	/**
	 * Gets the object which contains the externalised configuration properties.
	 * 
	 * <p>
	 * Some settings for this application can be easily changed in a text file without touching the 
	 * source code. These settings are available from this object. 
	 * 
	 * @return The configuration object.
	 */
	public static Settings getSettings() {
		return configuration;
	}
	
	/**
	 * Parses the command line arguments passed to this application.
	 * 
	 * <p>
	 * A set of default options are taken from {@link CommandLineOptions#getDefaultOptions()}. This
	 * application could add its own additional options, if required.
	 * 
	 * <p>
	 * If the command line can't be parsed, a help text is displayed on STDOUT and the application
	 * exits.
	 * 
	 * @param args The command line arguments passed to this application.
	 * 
	 * @author Moritz Marseu
	 * 
	 * @return The parsed command line.
	 */
	private static CommandLine parseCommandLine(String[] args) {
  
		Options options = CommandLineOptions.getDefaultOptions();

		DefaultParser parser = new DefaultParser();
     
		try {
         
			return parser.parse(options, args);
     
		} catch (ParseException e) {
         
			HelpFormatter help = new HelpFormatter();
         
			help.printHelp("java -jar <jar_file>", options);
         
			System.exit(1);
         
			return null;
		}
	}

}



