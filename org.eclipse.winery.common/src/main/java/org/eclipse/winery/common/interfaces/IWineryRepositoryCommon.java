/*******************************************************************************
 * Copyright (c) 2013-2016 University of Stuttgart.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and the Apache License 2.0 which both accompany this distribution,
 * and are available at http://www.eclipse.org/legal/epl-v10.html
 * and http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Oliver Kopp - initial API and implementation
 *     Lukas Harzenetter, Nicole Keppler - forceDelete for Namespaces
 *     Tino Stadlmaier, Philipp Meyer - rename for id/namespace
 *******************************************************************************/
package org.eclipse.winery.common.interfaces;

import java.io.IOException;
import java.util.Optional;

import org.eclipse.winery.common.ids.GenericId;
import org.eclipse.winery.common.ids.Namespace;
import org.eclipse.winery.common.ids.definitions.CapabilityTypeId;
import org.eclipse.winery.common.ids.definitions.NodeTypeImplementationId;
import org.eclipse.winery.common.ids.definitions.RelationshipTypeId;
import org.eclipse.winery.common.ids.definitions.RequirementTypeId;
import org.eclipse.winery.common.ids.definitions.ServiceTemplateId;
import org.eclipse.winery.common.ids.definitions.TOSCAComponentId;
import org.eclipse.winery.model.tosca.Definitions;
import org.eclipse.winery.model.tosca.TCapabilityType;
import org.eclipse.winery.model.tosca.TNodeTypeImplementation;
import org.eclipse.winery.model.tosca.TRelationshipType;
import org.eclipse.winery.model.tosca.TRequirementType;
import org.eclipse.winery.model.tosca.TServiceTemplate;

/**
 * Enables access to the winery repository via Ids defined in package {@link org.eclipse.winery.common.ids}
 *
 * Methods are moved from {@link org.eclipse.winery.repository.backend.IGenericRepository} to here as soon there is an
 * implementation for them. The ultimate goal is to eliminate IGenericRepository
 *
 * These methods are shared between {@link IWineryRepository} and {@link org.eclipse.winery.repository.backend.IRepository}
 */
public interface IWineryRepositoryCommon {

	/**
	 * Loads the TDefinition element belonging to the given id. Undefined behavior if <code>!exists(id)</code>
	 *
	 * @param id the TOSCAComponentId to load
	 * @return Optional.empty() if the definition of the given id does not exist
	 */
	Optional<Definitions> getDefinitions(TOSCAComponentId id);

	// in case one needs a new element, just copy and paste one of the following methods and adapt it.

	default Optional<TNodeTypeImplementation> getElement(NodeTypeImplementationId id) {
		return this.getDefinitions(id).map(definitions -> (TNodeTypeImplementation) definitions.getElement());
	}

	default Optional<TRelationshipType> getElement(RelationshipTypeId id) {
		return this.getDefinitions(id).map(definitions -> (TRelationshipType) definitions.getElement());
	}

	default Optional<TServiceTemplate> getElement(ServiceTemplateId id) {
		return this.getDefinitions(id).map(definitions -> (TServiceTemplate) definitions.getElement());
	}

	default Optional<TCapabilityType> getElement(CapabilityTypeId id) {
		return this.getDefinitions(id).map(definitions -> (TCapabilityType) definitions.getElement());
	}

	default Optional<TRequirementType> getElement(RequirementTypeId id) {
		return this.getDefinitions(id).map(definitions -> (TRequirementType) definitions.getElement());
	}

	/**
	 * Deletes the TOSCA element <b>and all sub elements</b> referenced by the given id from the repository
	 *
	 * We assume that each id is a directory
	 */
	void forceDelete(GenericId id) throws IOException;

	/**
	 * Renames a TOSCA component id
	 *
	 * @param oldId the old id
	 * @param newId the new id
	 */
	void rename(TOSCAComponentId oldId, TOSCAComponentId newId) throws IOException;

	/**
	 * Deletes all TOSCA components nested in the given namespace
	 *
	 * @param toscaComponentIdClazz the type of TOSCA components to delete
	 * @param namespace             the namespace to delete
	 */
	void forceDelete(Class<? extends TOSCAComponentId> toscaComponentIdClazz, Namespace namespace) throws IOException;
}
