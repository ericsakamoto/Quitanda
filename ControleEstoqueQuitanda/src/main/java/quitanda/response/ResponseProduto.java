package quitanda.response;

import quitanda.models.Produto;

public class ResponseProduto extends Response{

	private Iterable<Produto> responseData;
	private Produto produto;
	
	public ResponseProduto(String returnCode, String responseMessage) {
		super(returnCode, responseMessage);
	}
	
	public ResponseProduto(String returnCode, String responseMessage, Iterable<Produto> responseData) {
		super(returnCode, responseMessage);
		this.responseData = responseData;
	}
	
	public ResponseProduto(String returnCode, String responseMessage, Produto produto) {
		super(returnCode, responseMessage);
		this.setProduto(produto);
	}
	
	public Iterable<Produto> getResponseData() {
		return responseData;
	}

	public void setResponseData(Iterable<Produto> responseData) {
		this.responseData = responseData;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
