package org.openmrs.module.erp.api;

import java.io.IOException;

import org.openmrs.module.erp.ErpActivator;
import org.openmrs.module.erp.ErpConstants;
import org.openmrs.module.erp.ErpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TestHelper {
	
	@Autowired
	private ErpActivator erpActivator;
	
	@Autowired
	@Qualifier(ErpConstants.COMPONENT_ERP_CONTEXT)
	protected ErpContext context;
	
	public void init() throws IOException {
		erpActivator.started();
	}
}
