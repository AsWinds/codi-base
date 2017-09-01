package com.codi.base.spring;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * 获取资源文件
 * 必须注入messageSource
 * <p>spring 需要配置</p>
 * <p>spring-boot已经有messageSource了</p>
 *
 * @author shi.pengyan
 * @date 2017-06-22 13:37
 */
@Slf4j
public class MessageContext {
    /**
     * 存储Locale对象的本地线程变量.
     */
    private static ThreadLocal<Locale> locale = new ThreadLocal<Locale>();

    /**
     * Spring的MessageSource.
     */
    @Setter
    private static MessageSource messageSource;

    public static String get(String code, Object... arguments) {
        Locale locale = MessageContext.locale.get();
        return messageSource.getMessage(code, arguments, (locale != null) ? locale : Locale.CHINESE);
    }

    /**
     * 获取Locale
     *
     * @return
     */
    public static Locale getLocale() {
        return MessageContext.locale.get();
    }

    /**
     * 设置Locale.
     * <p>
     * 设置的locale将存储到ThreadLocal中供线程调用.
     * </p>
     *
     * @param locale 需设定的Locale对象
     */
    public static void setLocale(Locale locale) {
        MessageContext.locale.set(locale);
    }

    /**
     * 清空
     */
    public static void clearLocale() {
        MessageContext.locale.remove();
    }


}
