package org.openmrs.module.erp.api;

import org.openmrs.module.erp.ErpActivator;
import org.openmrs.module.erp.ErpConstants;
import org.openmrs.module.erp.ErpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestHelper {
	
	@Autowired
	private ErpActivator erpActivator;
	
	@Autowired
	@Qualifier(ErpConstants.COMPONENT_ERP_ORDER_SERVICE)
	protected ErpContext context;
	
	public void init() throws IOException {
		erpActivator.started();
	}
}
