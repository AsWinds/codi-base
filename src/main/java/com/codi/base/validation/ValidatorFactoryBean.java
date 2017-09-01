package com.codi.base.validation;

import javax.validation.Validator;

import org.springframework.beans.factory.FactoryBean;

public class ValidatorFactoryBean implements FactoryBean<Validator> {

	@Override
	public Validator getObject() throws Exception {
		return ValidationUtil.validator();
	}

	@Override
	public Class<?> getObjectType() {
		return Validator.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
