package org.alvin.cishan.common.exception;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class DefaultExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionMessage processException(Locale locale, Exception ex) {
		logger.error(ex.getMessage(), ex);
		return new ExceptionMessage(ErrorCode.EGENERALEXCEPTION,
				messageSource.getMessage(String.valueOf(ErrorCode.EGENERALEXCEPTION), null, locale));
	}

	@ExceptionHandler({ ParameterException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionMessage processParameterException(ParameterException ex, Locale locale) {
		String msg = ex.getMessage() + (ex.getDetail() == null ? "" : ":" + Joiner.on(",").join(ex.getDetail()));
		logger.warn(msg);
		String frontMsg = messageSource.getMessage(String.valueOf(ex.getCode()), ex.getVars(), locale);
		if (ex.getDetail() != null) {
			frontMsg = ex.getState().equals(ParameterException.PERMIT) ? "[warn]" + frontMsg : "[error]" + frontMsg;
		}
		return new ExceptionMessage(ex.getCode(), frontMsg, ex.getDetail());
	}

	@ExceptionHandler({ BusinessException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionMessage processBusinessException(Locale locale, BusinessException ex) {
		logger.error(ex.getMessage());
		return new ExceptionMessage(ex.getCode(), messageSource.getMessage(String.valueOf(ex.getCode()), null, locale));
	}

	// @ExceptionHandler({AuthorizationException.class})
	// @ResponseStatus(HttpStatus.UNAUTHORIZED)
	// @ResponseBody
	// public ExceptionMessage processAuthorizationException(Locale locale,
	// AuthorizationException ex) {
	// logger.error(ex.getMessage());
	// return new ExceptionMessage(ex.getCode(),
	// messageSource.getMessage(String.valueOf(ex.getCode()), null, locale));
	// }

	public static class ExceptionMessage {
		private Integer code;
		private String error;
		private List<?> detail;

		public ExceptionMessage(Integer code, String error) {
			this.code = code;
			this.error = error;
		}

		public ExceptionMessage(Integer code, String error, List<?> detail) {
			this.code = code;
			this.error = error;
			this.detail = detail;
		}

		public Integer getCode() {
			return code;
		}

		public Object getError() {
			return error;
		}

		public List<?> getDetail() {
			return detail;
		}
	}
}
