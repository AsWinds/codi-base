package com.codi.base.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 带签名的基类
 *
 * @author shi.pengyan
 * @version 1.0 2017-06-28 15:53
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class BaseSignDomain extends BaseDomain {

    private String sign;
}
