package com.codi.base.util;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {

    private boolean allowEmpty = true;
    private DateFormat dateFormat = null;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            try {
                if (this.dateFormat != null)
                    setValue(this.dateFormat.parse(text));
                else {
                    if (text.contains(":")) {
                        DateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        setValue(timeformat.parse(text));
                    } else {
                        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                        setValue(dateformat.parse(text));
                    }
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if (dateFormat == null)
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (value != null ? dateFormat.format(value) : "");
    }
}
