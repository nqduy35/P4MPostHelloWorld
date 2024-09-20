/*******************************************************************************
 * Copyright (c) 2024 DFKI.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     DFKI - Tapanta Bhanja <tapanta.bhanja@dfki.de>
 *	  FESTO - Moritz Marseu <moritz.marseu@festo.com>
 *******************************************************************************/

/**
 * ConnectedDevices is a general class provided for the AAS and its submodel to 
 * connect and communicate to real-world assets over different server protocols.
 * OPCUA and HTTP Protocols are currently supported with the corresponding wrappers
 * (OPCUAConnectorWrapper and HTTPConnectorWrapper).
 */
 
package aasmyasset.connection;

import java.security.GeneralSecurityException;

import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnector;
import org.eclipse.basyx.vab.protocol.opcua.connector.ClientConfiguration;
import org.eclipse.basyx.vab.protocol.opcua.connector.IOpcUaClient;
import org.eclipse.basyx.vab.protocol.opcua.connector.milo.MiloOpcUaClient;

import aasmyasset.module.AASServer;
import aasmyasset.module.Settings;

import org.eclipse.basyx.vab.protocol.api.IBaSyxConnector;
import com.festo.aas.p4m.security.SelfSignedCertificateProvider;

public class ConnectedDevices {
	

	/**
	 * Constructor. Initializes the available servers endpoints declared as field variables respective to their Connectors.
	 *  
	 * @throws Exception If any exception occurs during the creation of the connectors. 
	 */
public ConnectedDevices() throws Exception {
		
		try {
			
			ClientConfiguration opcUaClientConfig = createOpcUaClientConfiguration();
		} catch (GeneralSecurityException e) {
			throw new Exception("Failed to create application certificate for the OPCUA Client.", e);
		}
		
	}
	
	/**
	 * Creates a new OPCUAClient for the given endpoint. 
	 * 
	 * @param endpointUrl			The endpoint the client will connect to. 
	 * @param opcUaClientConfig		The configuration for the OPCUA client. 
	 * @return						A preconfigured {@link IOpcUaClient}.
	 */
	private IOpcUaClient createOPCUAClient(String endpointUrl, ClientConfiguration opcUaClientConfig) {
		
		IOpcUaClient client = new MiloOpcUaClient(endpointUrl);
		client.setConfiguration(opcUaClientConfig);
		
		return client;
	}
	
    /**
     * Creates a configuration object for OPC UA clients.
     *
     * <p>
     * You can make modifications to this method if you need to make other settings for your OPC UA
     * clients or if you'd like to use a different source for your application certificate.
     *
     * @return A new client configuration which can be applied to {@link IOpcUaClient} instances.
     *
     * @throws GeneralSecurityException if the creation of the application certificate for the OPC UA
     *                                  client fails.
     */
	private ClientConfiguration createOpcUaClientConfiguration() throws GeneralSecurityException {
		
		Settings settings = AASServer.getSettings();
		
		SelfSignedCertificateProvider certificateProvider = new SelfSignedCertificateProvider(settings.aasName.get(), null, null, null, null, null, settings.aasUri.get().toString());
		certificateProvider.load();
	       
	    ClientConfiguration config = new ClientConfiguration();
	    config.setKeyPairAndCertificate(certificateProvider.getKeyPair(), 
	    		certificateProvider.getCertificate());
	       
	    config.setApplicationName(settings.aasName.get());
	    config.setApplicationUri(settings.aasUri.get().toString());
	       
	    return config;
	    
	}
	
	/**
	 * Creates a new HTTPClient. 
	 * 
	 * @param endpointUrl		The HTTP endpoint, the client wants to connect to. 
	 * @return					A pre-configured {@link IBaSyxConnector}.
	 */
	private IBaSyxConnector createHTTPClient(String endpointUrl) {
		return new HTTPConnector(endpointUrl);
	}
	
}
