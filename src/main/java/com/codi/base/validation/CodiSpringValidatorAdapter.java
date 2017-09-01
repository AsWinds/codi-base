package com.codi.base.validation;

import javax.validation.Validator;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;


public class CodiSpringValidatorAdapter extends SpringValidatorAdapter {

	public CodiSpringValidatorAdapter(Validator targetValidator) {
		super(targetValidator);
	}

}
