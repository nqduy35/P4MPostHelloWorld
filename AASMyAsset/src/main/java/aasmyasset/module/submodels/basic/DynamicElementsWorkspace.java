/**
 * Copyright (c) 2022 CEA LIST and others.
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *  Contributors:
 *  	CEA LIST - Initial API and implementation
 */
package aasmyasset.module.submodels.basic;

import aasmyasset.connection.ConnectedDevices;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;
import javax.xml.datatype.Duration;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.protocol.opcua.types.NodeId;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;

public class DynamicElementsWorkspace {
	private ConnectedDevices connectedDevices;

	public DynamicElementsWorkspace(ConnectedDevices connectedDevices) {
		this.connectedDevices = connectedDevices;
	}

	/**
		 * [Note:] The method signature, if populated with parameters, are the 
		 * Input and InOutput parameters assigned by the user to the Operation. 
		 * 
		 * Papyrus4Manufacturing handles InOutput parameters as Input Parameters.  
		 * 
		 * Please under no circumstances change/modify this method's Signature 
		 * and the return statement. 
		 * 
		 * After this methods behaviour has been defined, please use the following 
		 * code line (as an example) to assign the output variable of this Operation its value, 
		 * if and only if an OutputVariable is defined for this Operation. 
		 * 
		 * Submodel1.out1_operation2_Output.setValue(<<Value Instance>>);
		 *  
		 */
	public void Basic_SayHello() {
		System.out.println("Hello");
	}

	/**
		 * [Note:] The method signature, if populated with parameters, are the 
		 * Input and InOutput parameters assigned by the user to the Operation. 
		 * 
		 * Papyrus4Manufacturing handles InOutput parameters as Input Parameters.  
		 * 
		 * Please under no circumstances change/modify this method's Signature 
		 * and the return statement. 
		 * 
		 * After this methods behaviour has been defined, please use the following 
		 * code line (as an example) to assign the output variable of this Operation its value, 
		 * if and only if an OutputVariable is defined for this Operation. 
		 * 
		 * Submodel1.out1_operation2_Output.setValue(<<Value Instance>>);
		 *  
		 */
	public void Basic_Addition(BigInteger IntegerA, BigInteger IntegerB) {
		System.out.println("Addition : " + (IntegerA.intValue() + IntegerB.intValue()));
	}

	/**
		 * [Note:] The method signature, if populated with parameters, are the 
		 * Input and InOutput parameters assigned by the user to the Operation. 
		 * 
		 * Papyrus4Manufacturing handles InOutput parameters as Input Parameters.  
		 * 
		 * Please under no circumstances change/modify this method's Signature 
		 * and the return statement. 
		 * 
		 * After this methods behaviour has been defined, please use the following 
		 * code line (as an example) to assign the output variable of this Operation its value, 
		 * if and only if an OutputVariable is defined for this Operation. 
		 * 
		 * Submodel1.out1_operation2_Output.setValue(<<Value Instance>>);
		 *  
		 */
	public void Basic_Print(String name) {
		System.out.println("Print : " + name);
	}

	/**
		 * [Note:] The method signature, if populated with parameters, are the 
		 * Input and InOutput parameters assigned by the user to the Operation. 
		 * 
		 * Papyrus4Manufacturing handles InOutput parameters as Input Parameters.  
		 * 
		 * Please under no circumstances change/modify this method's Signature 
		 * and the return statement. 
		 * 
		 * After this methods behaviour has been defined, please use the following 
		 * code line (as an example) to assign the output variable of this Operation its value, 
		 * if and only if an OutputVariable is defined for this Operation. 
		 * 
		 * Submodel1.out1_operation2_Output.setValue(<<Value Instance>>);
		 *  
		 */
	public void Basic_Subtraction(Float FloatA, Float FloatB) {
		System.out.println("Subtraction : " + (FloatA - FloatB));
	}

}
