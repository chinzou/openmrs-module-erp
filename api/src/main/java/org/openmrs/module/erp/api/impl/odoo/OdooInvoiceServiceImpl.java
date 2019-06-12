package org.openmrs.module.erp.api.impl.odoo;

import com.odoojava.api.FilterCollection;
import com.odoojava.api.ObjectAdapter;
import com.odoojava.api.Row;
import com.odoojava.api.RowCollection;
import com.odoojava.api.Session;
import org.openmrs.api.APIException;
import org.openmrs.module.erp.ErpConstants;
import org.openmrs.module.erp.Filter;
import org.openmrs.module.erp.api.ErpInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(ErpConstants.COMPONENT_ODOO_INVOICE_SERVICE)
public class OdooInvoiceServiceImpl implements ErpInvoiceService {
	
	private static final String INVOICE_MODEL = "account.invoice";
	
	private ArrayList<String> invoiceDefaultAttributes = new ArrayList<String>(Arrays.asList("name", "amount_total",
	    "state", "pricelist_id", "payment_term_id", "invoice_status", "origin", "create_date", "currency_id"));
	
	private Session session;
	
	@Autowired
	private OdooSession odooSession;
	
	public OdooInvoiceServiceImpl() {
	}
	
	public OdooInvoiceServiceImpl(OdooSession odooSession) {
		this.session = odooSession.getSession();
	}
	
	@Override
	public List<String> defaultModelAttributes() {
		return invoiceDefaultAttributes;
	}
	
	@Override
	public Map<String, Object> getInvoiceById(String invoiceId) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		if (this.session == null) {
			this.session = odooSession.getSession();
		}
		try {
			this.session.startSession();
			ObjectAdapter orderAdapter = this.session.getObjectAdapter(INVOICE_MODEL);
			FilterCollection filters = new FilterCollection();
			String[] fields = orderAdapter.getFieldNames();
			
			filters.clear();
			filters.add("id", "=", invoiceId);
			RowCollection records = orderAdapter.searchAndReadObject(filters, fields);
			if ((records != null) && (!records.isEmpty())) {
				
				Row record = records.get(0);
				for (String field : fields) {
					Object value = record.get(field);
					response.put(field, value);
				}
				response.put("invoice_lines", getInvoiceLinesByInvoiceId(invoiceId));
			}
		}
		catch (Exception e) {
			throw new APIException("Error while reading data from ERP server", e);
		}
		return response;
	}
	
	@Override
	public List<Map<String, Object>> getInvoicesByFilters(List<Filter> filters) {
		
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		if (this.session == null) {
			this.session = odooSession.getSession();
		}
		try {
			session.startSession();
			ObjectAdapter orderAdapter = session.getObjectAdapter(INVOICE_MODEL);
			FilterCollection filterCollection = new FilterCollection();
			String[] fields = orderAdapter.getFieldNames();
			
			filterCollection.clear();
			
			for (Filter filter : filters) {
				filterCollection.add(filter.getFieldName(), filter.getComparison(), filter.getValue());
			}
			
			RowCollection records = orderAdapter.searchAndReadObject(filterCollection, fields);
			if ((records != null) && (!records.isEmpty())) {
				for (Row record : records) {
					Map<String, Object> result = new HashMap<String, Object>();
					for (String field : fields) {
						Object value = record.get(field);
						result.put(field, value);
					}
					String invoiceId = String.valueOf(result.get("id"));
					result.put("invoice_lines", getInvoiceLinesByInvoiceId(invoiceId));
					response.add(result);
				}
			}
		}
		catch (Exception e) {
			throw new APIException("Error while reading data from ERP server", e);
		}
		return response;
	}
	
	private List<Map<String, Object>> getInvoiceLinesByInvoiceId(String invoiceId) {
		
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			session.startSession();
			ObjectAdapter orderAdapter = session.getObjectAdapter("account.invoice.line");
			FilterCollection filterCollection = new FilterCollection();
			String[] fields = orderAdapter.getFieldNames();
			
			filterCollection.clear();
			
			filterCollection.add("invoice_id", "=", invoiceId);
			
			RowCollection records = orderAdapter.searchAndReadObject(filterCollection, fields);
			if ((records != null) && (!records.isEmpty())) {
				for (Row record : records) {
					Map<String, Object> result = new HashMap<String, Object>();
					for (String field : fields) {
						Object value = record.get(field);
						result.put(field, value);
					}
					response.add(result);
				}
			}
		}
		catch (Exception e) {
			throw new APIException("Error while reading data from ERP server", e);
		}
		return response;
	}
	
}
