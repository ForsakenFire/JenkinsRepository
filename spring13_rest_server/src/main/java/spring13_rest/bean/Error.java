package spring13_rest.bean;

public class Error {
	
	public Error(String message) {
		this.message = message;
	}
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
