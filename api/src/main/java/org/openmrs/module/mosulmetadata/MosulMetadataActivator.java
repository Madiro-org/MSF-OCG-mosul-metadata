/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.mosulmetadata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.dataexchange.DataImporter;
import org.openmrs.api.context.Context;

import org.openmrs.module.Module;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class MosulMetadataActivator implements ModuleActivator {

	protected Log log = LogFactory.getLog(getClass());

	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing Mosul Metadata Module");
	}

	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("Mosul Metadata Module refreshed");
	}

	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting Mosul Metadata Module");
	}

	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		log.info("Mosul Metadata Module started");
		// try {
		// 	DataImporter dataImporter = Context.getRegisteredComponent("dataImporter", DataImporter.class);

		// 	log.info("Start import of Mosul Privileges");
        //     dataImporter.importData("metadata/Role_Privilege.xml");
        //     log.info("Mosul Privileges Imported");
			
        // } catch (Exception e) {
        //     Module mod = ModuleFactory.getModuleById("mosulmetadata");
        //     ModuleFactory.stopModule(mod);
        //     throw new RuntimeException("failed to setup the module ", e);
        // }
	}

	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping Mosul Metadata Module");
	}

	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		log.info("Mosul Metadata Module stopped");
	}
}
