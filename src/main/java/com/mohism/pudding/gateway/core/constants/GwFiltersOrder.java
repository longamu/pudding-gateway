package com.mohism.pudding.gateway.core.constants;

/**
 * 网关的常量
 *
 * @author fengshuonan
 * @date 2018-05-09-下午3:38
 */
public interface GwFiltersOrder {

    /**
     * 请求号生成器过滤器顺序
     */
    int REQUEST_NO_GENERATE_FILTER_ORDER = -10;

    /**
     * jwt token验证的过滤器顺序
     */
    int JWT_TOKEN_FILTER_ORDER = 20;

    /**
     * 路径资源校验的顺序
     */
    int PATH_MATCH_FILTER_ORDER = 40;

}
