package com.codi.base.spring;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * Created by Shangdu Lin on 2017/3/6 17:41.
 */
public class CodiJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入,因为Job对象的实例化过程是在Quartz中进行的，而Service是在Spring容器当中的，所有Job中注入Spring对象，只能通过这种方式来注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
