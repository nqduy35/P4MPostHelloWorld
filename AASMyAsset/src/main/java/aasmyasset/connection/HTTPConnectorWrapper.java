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
 ******************************************************************************/
package aasmyasset.connection;
import org.eclipse.basyx.vab.protocol.api.IBaSyxConnector;

/**
 * Wrapper around BaSyx' HTTP Connector with a simpler API.
 * 
 * <h2>Introduction</h2>
 * This class encapsulates the HTTPConnector class provided by BaSyx. This helps
 * in providing the user not only with better documented methods, but also helps
 * abstract methods or functionalities a basic user does not need.
 * 
 *  The user can use this class to perform the following:
 *  <ul>
 *  <li> Read values from a HTTP Server Endpoint.
 *  <li> Write values to a HTTP Server Endpoint.
 *  <li> Invoke Methods on a HTTP Server at the given Endpoint. 
 *  </ul>
 *  
 *  All of these requires the user to specify the URL where the server is hosted 
 *  along with the routing to the Endpoint where a value could be written to, 
 *  read from or methods could be invoked. 
 *  
 *  An Endpoint URL, is how resources are located on the HTTP Server. This 
 *  endpoint is broadly divided into two main components. 
 *  
 *   <ul>
 *   <li> The IP Address of the server, where the resources are being hosted. 
 *   This might be either in the form
 *   http://example.com/ or http://127.0.0.1:5000/ or as the server was defined. 
 *   This must be known by the user. 
 *   
 *   <li> The navigation path to the required resource. The forward slash (/) is 
 *   used here to indicate hierarchical relationships in the resource. 
 *   Thus using the forward slash (/) one could navigate inside the resource to 
 *   other hierarchically related resources.  
 *   </ul>
 *   
 *   An example for this would be - The HTTP server end point 
 *   'http://127.0.0.1:5000/' hosting a resource named 'login'  would be 
 *   written as:
 *   
 *   urlPath = http://127.0.0.1:5000/login
 *
 */


public final class HTTPConnectorWrapper {
	
	/**
	 * The BaSyx HTTP Client. One could use this final field/member variable 
	 * directly to access the Connector provided by BaSyx, with more 
	 * advanced methods. 
	 */
	
	public final IBaSyxConnector baSyxHTTPClient;
	
	/**
	 * Constructor method for the class HTTPConnectorWrapper. It requires as 
	 * parameters within the parenthesis an instance of the IBaSyxConnector 
	 * interface. The class {@link ConnectorFactory.java} provides the
	 * 
	 * @param baSyxHTTPClient
	 */

	public HTTPConnectorWrapper(IBaSyxConnector baSyxHTTPClient) {
		
		this.baSyxHTTPClient = baSyxHTTPClient;
		
	}
	
	/**
	 *	Performs a HTTP GET.
	 *
	 *	The GET method means retrieve whatever information (in the form of an
	 *	entity) is identified by the Request-URI. If the Request-URI refers
	 *	to a data-producing process, it is the produced data which shall be
	 *	returned as the entity in the response and not the source text of the
	 *	process, unless that text happens to be the output of the process. 
	 *  
	 *  [For further info, refer - RFC 2616, � 9.3 
	 *  Link: https://datatracker.ietf.org/doc/html/rfc2616#section-9.3]
	 *   
	 * @param urlPath	The URL Path (Request-URI) where the resource being 
	 * 					requested is located.
	 * @return			String representation of the resource available under
	 * 					the mentioned urlPath. 
	 */
	public String readValue(String urlPath) {
		return baSyxHTTPClient.getValue(urlPath);
	}
	
	/**
	 * Performs a HTTP PUT (or) a HTTP POST.
	 * 
	 * The PUT method requests that the enclosed entity be stored under the
	 * supplied Request-URI. If the Request-URI refers to an already
	 * existing resource, the enclosed entity SHOULD be considered as a
	 * modified version of the one residing on the origin server. If the
	 * Request-URI does not point to an existing resource, and that URI is
	 * capable of being defined as a new resource by the requesting user
	 * agent, the origin server can create the resource with that URI.
	 * 
	 * [For more details and further info, refer - RFC 2616 � 9.6
	 * Link: https://datatracker.ietf.org/doc/html/rfc2616#section-9.6]
	 * 
	 * The POST method is used to request that the origin server accept the
	 * entity enclosed in the request as a new subordinate of the resource
	 * identified by the Request-URI in the Request-Line. POST is designed
	 * to allow a uniform method to cover varied functions. The actual function 
	 * performed by the POST method is determined by the server and is usually 
	 * dependent on the Request-URI.
	 * 
	 * [For more details on varied functions and further info, refer - RFC 2616 
	 * � 9.5
	 * Link: https://datatracker.ietf.org/doc/html/rfc2616#section-9.5]
	 * 
	 * @param urlPath		The URL Path (Request-URI) where the resource being 
	 * 						requested is located.
	 * @param value			The "entity" (as mentioned in the definitions above) 
	 * 						that is to be used for 'PUT' and 'POST' requests. 
	 * @param requestType	Type of the request to be made using the function. 
	 * 						Should be mentioned either "PUT" (or) "POST".
	 * @return				Returns the feedback from the respective HTTP 
	 * 						requests that have been made. 
	 * 						In case an unrecognized requestType is passed in by
	 * 						user, the method returns "Invalid Request".
	 */
	public String writeValue(String urlPath, Object value, String requestType) {
		
		if (requestType=="PUT") {
			return baSyxHTTPClient.setValue(urlPath, (String) value);
		}
		
		else if (requestType == "POST") {
			return baSyxHTTPClient.createValue(urlPath, (String) value);
		}
		
		else {
			return "Invalid Request";
		}
	}
	
	/**
	 * Performs a HTTP POST Operation. 
	 * 
	 * The POST method is used to request that the origin server accept the
	 * entity enclosed in the request as a new subordinate of the resource
	 * identified by the Request-URI in the Request-Line. POST is designed
	 * to allow a uniform method to cover varied functions. The actual function 
	 * performed by the POST method is determined by the server and is usually 
	 * dependent on the Request-URI.
	 * 
	 * [For more details on varied functions and further info, refer - RFC 2616 
	 * � 9.5
	 * Link: https://datatracker.ietf.org/doc/html/rfc2616#section-9.5]
	 * 
	 * @param urlPath		The URL Path (Request-URI) where the resource being 
	 * 						requested is located.
	 * @param value			The "entity" (as mentioned in the definitions above) 
	 * 						that is to be used for 'PUT' and 'POST' requests.
	 * @return				Returns the feedback from the HTTP POST request. 
	 */
	public String invokeMethod(String urlPath, Object value) {
		return baSyxHTTPClient.invokeOperation(urlPath, (String) value);
		
	}

}

