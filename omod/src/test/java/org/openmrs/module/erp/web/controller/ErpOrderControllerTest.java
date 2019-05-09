package org.openmrs.module.erp.web.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.erp.ErpContext;
import org.openmrs.module.erp.api.TestHelper;
import org.openmrs.module.erp.api.utils.ErpProperties;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

public class ErpOrderControllerTest extends BaseModuleWebContextSensitiveTest {
	
	private void setErpProperties() {
		Properties erpProperties = new Properties();
		erpProperties.put("erp.host", "localhost");
		erpProperties.put("erp.port", "8069");
		erpProperties.put("erp.database", "odoo");
		erpProperties.put("erp.user", "admin");
		erpProperties.put("erp.password", "admin");
		ErpProperties.initalize(erpProperties);
		
	}
	
	@Autowired
	private ErpContext erpContext;
	
	@Autowired
	private TestHelper testHelper;
	
	@Autowired
	private ErpOrderController erpOrderController;
	
	public ErpOrderControllerTest() {
		super();
		setErpProperties();
	}
	
	@Before
	public void setup() throws IOException {
		testHelper.init();
	}
	
	@Test
	public void erpOrderControllerShouldReturnResponse() throws IOException {
		HttpServletResponse response = mock(HttpServletResponse.class);
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		erpOrderController.getErpOrdersByPatientUuid("4", response);
		
	}
	
}
