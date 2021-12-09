package  com.segurosbolivar.avaluos.modelo.service.util;


public class SearchExpressionOrder {

	private String field;
	private String order;


	public SearchExpressionOrder() {
		super();
	}

	public SearchExpressionOrder(String field, String order) {
		super();
		this.field = field;
		this.order = order;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}


}