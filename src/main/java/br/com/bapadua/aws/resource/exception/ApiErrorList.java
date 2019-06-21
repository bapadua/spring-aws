package br.com.bapadua.aws.resource.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorList extends ApiError {
	private static final long serialVersionUID = 1L;

	private List<String> errors;

	public ApiErrorList(int code, String msg, Date date, List<String> errors) {
		super(code, msg, date);
		this.errors = errors;
	}

}
