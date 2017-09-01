package com.codi.base.validation.impl;

import java.util.Arrays;
import java.util.HashSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.codi.base.validation.annotation.Strings;

public class StringsConstraintValidator implements ConstraintValidator<Strings, String> {
	
	private Strings setting;

	@Override
	public void initialize(Strings constraintAnnotation) {
		this.setting = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		HashSet<String> vals = new HashSet<>(Arrays.asList(setting.values()));
		if (vals.contains(value)) {
			return true;
		}
		return false;
	}

}
