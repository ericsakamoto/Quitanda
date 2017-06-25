package quitanda.response;

import quitanda.models.Compra;

public class ResponseCompra extends Response{

	private Iterable<Compra> responseData;
	private Float soma;
	
	
	public ResponseCompra(String returnCode, String responseMessage) {
		super(returnCode, responseMessage);
	}
	
	public ResponseCompra(String returnCode, String responseMessage, Iterable<Compra> responseData, Float soma) {
		super(returnCode, responseMessage);
		this.responseData = responseData;
		this.soma = soma;
	}
	
	public Iterable<Compra> getResponseData() {
		return responseData;
	}

	public void setResponseData(Iterable<Compra> responseData) {
		this.responseData = responseData;
	}

	public Float getSoma() {
		return soma;
	}

	public void setSoma(Float soma) {
		this.soma = soma;
	}	
}
