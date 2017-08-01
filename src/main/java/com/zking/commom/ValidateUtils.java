package com.zking.commom;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public class ValidateUtils {

	public static <T> String validate(T t, Validator validate) {
		Set<ConstraintViolation<T>> a = validate.validate(t);
		//**如果 验证通过
		if(a.isEmpty()){
			return "0:success";
		}
		ConstraintViolation<T> cl = a.iterator().next();
		return "3:"+cl.getMessage();
	}

}
