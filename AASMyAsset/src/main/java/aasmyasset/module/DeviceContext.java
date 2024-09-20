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
package aasmyasset.module;

import javax.servlet.http.HttpServlet;
import org.eclipse.basyx.aas.restapi.AASModelProvider;
import org.eclipse.basyx.aas.restapi.MultiSubmodelProvider;
import org.eclipse.basyx.submodel.restapi.SubmodelProvider;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxContext;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;

/**
 * Device Context returns an Object of Type 'BaSyxContext' for each SubModels. 
 */
public final class DeviceContext {
	
	public static BaSyxContext forModels(AASModels models) {
		
		String hostName = AASServer.getSettings().applicationHostname.get();
		int port = AASServer.getSettings().applicationPort.get();
		BaSyxContext context = new BaSyxContext("", "", hostName, port);
		
		MultiSubmodelProvider aasProvider = new MultiSubmodelProvider();
		aasProvider.setAssetAdministrationShell(new AASModelProvider(models.aas));
		
		models.getSubmodels().forEach((sm) -> {
			aasProvider.addSubmodel(new SubmodelProvider(sm.getSubmodel()));
		});
		
		HttpServlet servlet = new VABHTTPInterface<IModelProvider>(aasProvider);
		context.addServletMapping("/*", servlet);
		
		return context;
		
	}
}
