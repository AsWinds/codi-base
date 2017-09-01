package com.codi.base.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.codi.base.common.Const;
import com.codi.base.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * MDC trace filter
 * 使用方式
 * <p>
 * <dubbo:consumer filter="MDCFilter"/>
 * <dubbo:provider filter="MDCFilter"/>
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-20 16:17
 * @since 1.0
 */
@Slf4j
public class MDCFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String globalMdcId;

        if (RpcContext.getContext().isConsumerSide()) {
            globalMdcId = MDC.get(Const.GLOBAL_MDC_ID);

            if (StringUtil.isEmpty(globalMdcId)) {
                globalMdcId = UUID.randomUUID().toString();
            }

            RpcContext.getContext().setAttachment(Const.GLOBAL_MDC_ID, globalMdcId);

        } else if (RpcContext.getContext().isProviderSide()) {

            globalMdcId = RpcContext.getContext().getAttachment(Const.GLOBAL_MDC_ID);

            if (StringUtil.isEmpty(globalMdcId)) {
                log.warn("请检查dubbo发起方GLOBAL_MDC_ID是否传递");
                globalMdcId = UUID.randomUUID().toString();
            }
            // 这里要存放一份，嵌套场景
            RpcContext.getContext().setAttachment(Const.GLOBAL_MDC_ID, globalMdcId);
            MDC.put(Const.GLOBAL_MDC_ID, globalMdcId);
        }

        return invoker.invoke(invocation);
    }
}
