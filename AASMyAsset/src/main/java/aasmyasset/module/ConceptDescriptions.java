/*******************************************************************************
 * Copyright (c) 2022 DFKI.
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
import java.util.ArrayList;
import java.util.Collection; 
import java.util.List;
import org.eclipse.basyx.aas.metamodel.map.parts.ConceptDictionary;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.IKey;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.parts.ConceptDescription;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key; 
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements; 
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;


/** Holds all concept descriptions known to this AAS. */
public class ConceptDescriptions extends ConceptDictionary {

    
    /**
     * Creates all concept descriptions and stores references to them in fields.
     */
    public ConceptDescriptions() {
        super();
        

   }
}

