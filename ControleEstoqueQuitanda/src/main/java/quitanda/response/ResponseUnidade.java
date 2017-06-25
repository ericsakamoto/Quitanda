package quitanda.response;

import quitanda.models.Unidade;

public class ResponseUnidade extends Response{

	private Iterable<Unidade> responseData;
	
	public ResponseUnidade(String returnCode, String respondeMessage) {
		super(returnCode, respondeMessage);
	}
	
	public ResponseUnidade(String returnCode, String respondeMessage, Iterable<Unidade> responseData) {
		super(returnCode, respondeMessage);
		this.responseData = responseData;
	}
	
	public Iterable<Unidade> getResponseData() {
		return responseData;
	}

	public void setResponseData(Iterable<Unidade> responseData) {
		this.responseData = responseData;
	}
	
	
	
}
