package org.com.zeus.common.config;

import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.exception.CustomerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalException {
	@ExceptionHandler()
	@ResponseBody
	BaseResullt handleCustomerException(CustomerException e) {
		log.error(e.getMessage(), e);
		return BaseResullt.utils.setFailMsg(e.getMessage());
	}

	@ExceptionHandler()
	@ResponseBody
	BaseResullt handleException(Exception e) {
		log.error(e.getMessage(), e);
		return BaseResullt.utils.setFailMsg("操作失败");
	}

}
