package com.mohism.pudding.gateway.modular.service.impl;

import com.mohism.pudding.gateway.core.exception.AuthExceptionEnum;
import com.mohism.pudding.gateway.modular.consumer.AuthServiceConsumer;
import com.mohism.pudding.gateway.modular.consumer.ResourceServiceConsumer;
import com.mohism.pudding.gateway.modular.service.TokenValidateService;
import com.mohism.pudding.kernel.model.exception.ServiceException;
import com.mohism.pudding.kernel.model.resource.ResourceDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 集成scanner的鉴权服务
 *
 * @author fengshuonan
 * @date 2018-08-13 21:52
 */
//@Service
public class WithScannerTokenValidateServiceImpl extends TokenValidateService {

    @Autowired
    private ResourceServiceConsumer resourceServiceConsumer;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Override
    public boolean validateToken(String token, HttpServletRequest request) {

        String requestURI = null;
        if (request != null) {
            //获取context-path加servlet-path
            requestURI = request.getRequestURI();

            //如果是zuul开头的url，则去掉zuul再去资源校验
            if (requestURI.startsWith("/zuul")) {
                requestURI = requestURI.substring(5);
            }
        }

        //获取当前接口是否需要鉴权
        ResourceDefinition currentResource = resourceServiceConsumer.getResourceByUrl(requestURI);
        if (currentResource == null) {
            return true;
        }

        //判断是否需要登录
        if (currentResource.getRequiredLogin()) {

            //验证token是否正确
            boolean flag = authServiceConsumer.checkToken(token);
            if (flag) {
                return true;
            } else {
                throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
            }
        } else {
            return true;
        }
    }
}
