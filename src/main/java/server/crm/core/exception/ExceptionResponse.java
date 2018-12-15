package server.crm.core.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import server.crm.responses.base.BaseResponse;

import java.util.Date;
@JsonPropertyOrder(value = {"timestamp", "message, details", "errors", "result"})
public class ExceptionResponse extends BaseResponse {

	private Date timestamp;
	private String message;
	private String details;

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}