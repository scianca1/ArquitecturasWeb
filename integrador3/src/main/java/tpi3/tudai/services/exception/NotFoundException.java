package tpi3.tudai.services.exception;

public class NotFoundException extends RuntimeException {
	
	private String message;
	

	public NotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
