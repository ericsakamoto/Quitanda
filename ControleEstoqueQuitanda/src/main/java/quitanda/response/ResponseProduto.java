package quitanda.response;

import quitanda.models.Produto;

public class ResponseProduto extends Response{

	private Iterable<Produto> responseData;
	
	public ResponseProduto(String returnCode, String respondeMessage) {
		super(returnCode, respondeMessage);
	}
	
	public ResponseProduto(String returnCode, String respondeMessage, Iterable<Produto> responseData) {
		super(returnCode, respondeMessage);
		this.responseData = responseData;
	}
	
	public Iterable<Produto> getResponseData() {
		return responseData;
	}

	public void setResponseData(Iterable<Produto> responseData) {
		this.responseData = responseData;
	}
	
	
	
}
