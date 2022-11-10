package kaur.srini.srinitrial.web;

import java.util.HashMap;
import java.util.Map;

public class ApiError {

	private String errorMessage;
	private Map<String, String> validationErrors;
	
	public ApiError() {

	}

	public ApiError(String errorMessage) {
		this.errorMessage = errorMessage;
		validationErrors = new HashMap<String, String>();
	}

	public ApiError(String errorMessage, Map<String, String> validationErrors) {
		this.errorMessage = errorMessage;
		this.validationErrors = validationErrors;
	}


	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
