package com.codi.base.validation;

import java.util.Locale;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

public class ValidationUtil {
	
	private static final Validator INSTANCE;
	
	static{
		INSTANCE = buildValidator();
	}
	
	private static Validator buildValidator(){
		HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
		configuration.failFast(true).ignoreXmlConfiguration();
		MessageInterpolator defaultInterpolator = configuration.getDefaultMessageInterpolator();
		configuration.messageInterpolator(new LocaleSpecifiedMessageInterpolator(defaultInterpolator, Locale.CHINESE));
		return configuration.buildValidatorFactory().getValidator();
	}
	
	public static Validator validator(){
		return INSTANCE;
	}
}
