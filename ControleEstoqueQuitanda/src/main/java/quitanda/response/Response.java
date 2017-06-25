package quitanda.response;

public class Response {

	private String returnCode;
	private String responseMessage;
	
	public Response() {
		
	}

	public Response(String returnCode, String respondeMessage) {
		this.returnCode = returnCode;
		this.responseMessage = respondeMessage;
	}
		
	public String getReturnCode() {
		return returnCode;
	}
	
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setResponseMessage(String respondeMessage) {
		this.responseMessage = respondeMessage;
	}	
	
}
