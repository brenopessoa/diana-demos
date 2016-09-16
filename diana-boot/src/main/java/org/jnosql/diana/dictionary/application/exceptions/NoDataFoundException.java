package org.jnosql.diana.dictionary.application.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "No data found")
public class NoDataFoundException extends RuntimeException {

}
