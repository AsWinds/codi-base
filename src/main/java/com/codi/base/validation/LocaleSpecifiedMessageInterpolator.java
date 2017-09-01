package com.codi.base.validation;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.springframework.util.Assert;


public class LocaleSpecifiedMessageInterpolator implements MessageInterpolator {

	private final MessageInterpolator targetInterpolator;
	private final Locale locale;


	/**
	 * Create a new LocaleContextMessageInterpolator, wrapping the given target interpolator.
	 * @param targetInterpolator the target MessageInterpolator to wrap
	 */
	public LocaleSpecifiedMessageInterpolator(MessageInterpolator targetInterpolator, Locale locale) {
		Assert.notNull(targetInterpolator, "Target MessageInterpolator must not be null");
		Assert.notNull(locale, "Target Locale must not be null");
		this.targetInterpolator = targetInterpolator;
		this.locale = locale;
	}


	@Override
	public String interpolate(String message, Context context) {
		return this.targetInterpolator.interpolate(message, context, locale);
	}

	@Override
	public String interpolate(String message, Context context, Locale locale) {
		return this.targetInterpolator.interpolate(message, context, locale);
	}

}
