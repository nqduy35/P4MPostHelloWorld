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
 *******************************************************************************/
package aasmyasset.module;

import java.io.IOException;

import com.festo.aas.p4m.configuration.AasProperties;
import com.festo.aas.p4m.configuration.LoadableProperty;
import com.festo.aas.p4m.validators.UrlValidator;

/**
 * Holds the settings for this AAS application. For more details, refer to {@link AasProperties}
 * @author DFKI
 *
 */

public final class Settings extends AasProperties {
	
		
	/**
	 * The URI identifying the Submodels. This serves as the submodel's global unique id.
	 * 
	 * <p>
	 * This is can be an URL[Uniform Resource Locator] (e.g. <code>http://www.example.com/myAAS1234/</code>), an URN
	 * [Uniform Resource Name] (e.g. <code>urn:uuid:6e8bc430-9c3a-11d9-9669-0800200c9a66</code>)
	 * 
	 * <p>
	 * This is merely an identifier. Even if an URL is specified, this URL isn't required to be accessible on any network. 
	 * 
	 */ 
	@LoadableProperty
	public final UriProperty SUBMODEL_BASIC_IRI = new UriProperty("submodel.Basic.uri");


	/**
	 * Creates a new settings object using the default properties file path. 
	 * 
	 *  <p>
	 *  Default values are automatically loaded from the resource file
	 *  {@link #DEFAULT_PROPERTIES_RESOURCE} within this constructor. User-defined values are loaded in 
	 *  {@link #load()} instead, which must be called, before any property values can be accessed. 
	 *  
	 * @throws IOException if the resource file with the default values can't be loaded.
	 * 
	 */
	protected Settings() throws IOException {
		super();
	}
	
	/**
	 * Creates a new settings object using the specified properties in the file path. 
	 * 
	 *  <p>
	 *  Default values are automatically loaded from the resource file
	 *  {@link #DEFAULT_PROPERTIES_RESOURCE} within this constructor. User-defined values are loaded in 
	 *  {@link #load()} instead, which must be called, before any property values can be accessed. 
	 *  
	 * @throws IOException if the resource file with the default values can't be loaded.
	 * 
	 */
	public Settings(String userFilepath) throws IOException {
		super(userFilepath);
	}

}

