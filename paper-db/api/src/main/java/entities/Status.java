package entities;

import java.util.HashMap;
import java.util.Map;

public class Status {
	private static Map<Integer, String> status = null;
	private Boolean success = null;
	private Integer errorCode = null;
	private String message = null;

	static {
		// Define errorcode for common errors.
		status = new HashMap<Integer, String>();
		status.put(0, "Self-defined Error");
		status.put(1, "Success");
		status.put(2, "User not Login");
		status.put(3, "User have No Authentication");
		status.put(4, "Data not exists");
	}
	
	public Status(Boolean success) {
		setSuccess(success);
		if (success) {
			setErrorCode(1);
		} else {
			setErrorCode(4);
		}
	}

	public Status(Boolean success, String message) {
		setSuccess(success);
		setErrorCode(0);
		setMessage(message);
	}

	public Status(Boolean success, Integer errorCode) {
		setSuccess(success);
		setErrorCode(errorCode);
		setMessage(status.get(errorCode));
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

}
