package org.openmrs.module.erp;

/**
 * Contains module's constants.
 */
public class ErpConstants {

	// Module resources

	public final static String MODULE_NAME = "Erp";

	public final static String MODULE_ARTIFACT_ID = "erp";

	public static final String MODULE_PRIVILEGE = "ERP PRIVILEGE";

	public static final String COMPONENT_ERP_CONTEXT = MODULE_ARTIFACT_ID + ".ErpContext";

	public static final String COMPONENT_ERP_ORDER_SERVICE = MODULE_ARTIFACT_ID + ".OrderService";
	// REST API resources

	public final static String ERP_ORDERS_URI = "/orders/{uuid}";

	public final static String ERP_ORDER_URI = "/order/{id}";
}
