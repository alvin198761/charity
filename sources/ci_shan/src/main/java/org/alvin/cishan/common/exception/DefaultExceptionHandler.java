package org.alvin.cishan.common.exception;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.RestfullResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
public class DefaultExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestfullResp<?> processException(Locale locale, Exception ex) {
		logger.error(ex.getMessage(), ex);
		return new RestfullResp(ErrorCode.EGENERALEXCEPTION,
				messageSource.getMessage(String.valueOf(ErrorCode.EGENERALEXCEPTION), null, locale));
	}

	@ExceptionHandler({ParameterException.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestfullResp<?> processParameterException(ParameterException ex, Locale locale) {
		String msg = ex.getMessage() + (ex.getDetail() == null ? "" : ":" + Joiner.on(",").join(ex.getDetail()));
		logger.warn(msg);
		String frontMsg = messageSource.getMessage(String.valueOf(ex.getCode()), ex.getVars(), locale);
		if (ex.getDetail() != null) {
			frontMsg = ex.getState().equals(ParameterException.PERMIT) ? "[warn]" + frontMsg : "[error]" + frontMsg;
		}
		return new RestfullResp(ex.getCode(), frontMsg, ex.getDetail());
	}

	@ExceptionHandler({BusinessException.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestfullResp<?> processBusinessException(Locale locale, BusinessException ex) {
		logger.error(ex.getMessage());
		return new RestfullResp(ex.getCode(), messageSource.getMessage(String.valueOf(ex.getCode()), null, locale));
	}

}
