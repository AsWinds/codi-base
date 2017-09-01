package com.codi.base.domain;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.codi.base.util.DateEditor;

/**
 * 抽象Controller基类
 * 
 * @author shi.pengyan
 * @date 2016年12月25日 下午1:52:22
 */
public abstract class BaseController {
    /* logger */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
}
