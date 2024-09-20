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

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.basyx.aas.metamodel.api.parts.asset.AssetKind;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.parts.Asset;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import com.festo.aas.p4m.connection.SubmodelWrapper;

import aasmyasset.module.submodels.basic.Basic; 

/**
 * Contains the major components of the AAS Model.
 * 
 * <p>
 * 'AAS Model' - includes the AAS itself the assets and the submodels. 
 * 
 * <p>
 * This class creates and exposes the AAS itself as well as its asset, concept descriptions and submodels. 
 * 
 * @author DFKI
 *
 */ 
public class AASModels { 

	/** The asset represented by this AAS. */
	public final Asset asset;
	
	/** The AAS object itself. */
	public final AssetAdministrationShell aas;
	
	/** Holds all concept descriptions. */
	public final ConceptDescriptions conceptDescriptions;

	/** List of Submodels. **/
	public final Set<SubmodelWrapper> listSubmodels = new HashSet<>();
	
		/** The submodel 'Basic'. */ 
	public final SubmodelWrapper Basic;
	
	
	/**
	 * Constructor Method.
	 * Creates all components of this AAS model and stores references to them in fields. 
	 */
	public AASModels() {
		
		Settings settings = AASServer.getSettings();	
		String assetIdShort = settings.assetName.get();
		URI assetId = settings.assetUri.get();			
		String aasIdShort = settings.aasName.get();
		URI aasId = settings.aasUri.get();
		
		
		aas = createAas(aasIdShort, aasId);
		
		asset = createAsset(assetIdShort, assetId);
		aas.setAsset(asset);
		
		conceptDescriptions = new ConceptDescriptions();
		aas.setConceptDictionary(Arrays.asList(conceptDescriptions));
		
		Basic = new SubmodelWrapper(new Basic());
		aas.addSubmodel(Basic.getSubmodel());
		listSubmodels.add(Basic); 
	}
	
	/**
	 * Creates an asset object with the given idShort and id. 
	 * 
	 * @param idShort	The asset's idShort. 
	 * @param id		The asset's globally unique id. Must be an IRI. (i.e., an URI)
	 * 
	 * @return			The created asset. 
	 */
	private Asset createAsset(String idShort, URI id) {
		
		Asset asset = new Asset();
		asset.setAssetKind(AssetKind.INSTANCE);
		asset.setIdShort(idShort);
		asset.setIdentification(IdentifierType.IRI, id.toString());
		
		return asset;
		
	}
	
	/**
	 * Creates an AAS object with the given idShort and id. 
	 * 
	 * @param idShort	The AAS' idShort.
	 * @param id		The AAS' globally unique id. Must be an IRI (i.e., an URI)
	 * 
	 * @return			The created AAS.
	 */
	private AssetAdministrationShell createAas(String idShort, URI id) {
		AssetAdministrationShell aas = new AssetAdministrationShell();
		aas.setIdShort(idShort);
		aas.setIdentification(IdentifierType.IRI, id.toString());
		
		return aas;
	}
	
	
	public Set<SubmodelWrapper> getSubmodels() {
		return listSubmodels;
	}

}
