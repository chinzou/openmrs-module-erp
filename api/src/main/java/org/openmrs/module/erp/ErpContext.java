package org.openmrs.module.erp;

import org.openmrs.module.emrapi.utils.ModuleProperties;
import org.openmrs.module.erp.api.ErpOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(ErpConstants.COMPONENT_ERP_CONTEXT)
public class ErpContext extends ModuleProperties {
	
	@Autowired
	@Qualifier(ErpConstants.COMPONENT_ERP_ORDER_SERVICE)
	protected ErpOrderService erpOrderService;
	
	public ErpOrderService getErpOrderService() {
		return erpOrderService;
	}
}
