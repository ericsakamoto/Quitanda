package quitanda.response;

import quitanda.models.Tipo;

public class ResponseTipo extends Response{

	private Iterable<Tipo> responseData;
	
	public ResponseTipo(String returnCode, String respondeMessage) {
		super(returnCode, respondeMessage);
	}
	
	public ResponseTipo(String returnCode, String respondeMessage, Iterable<Tipo> responseData) {
		super(returnCode, respondeMessage);
		this.responseData = responseData;
	}
	
	public Iterable<Tipo> getResponseData() {
		return responseData;
	}

	public void setResponseData(Iterable<Tipo> responseData) {
		this.responseData = responseData;
	}
}
