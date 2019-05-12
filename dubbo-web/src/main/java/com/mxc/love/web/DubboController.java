package com.mxc.love.web;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.mxc.love.model.User;
import com.mxc.love.service.UserService;
import com.mxc.love.web.handler.ApiResult;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DubboController {

    @Reference(version = "${demo.service.version}")
    private UserService demoService;

    @NacosValue(value = "${name:unknown}" ,autoRefreshed = true)
    private String name;

    private boolean isTrue = true;

    @Autowired
    private User user;

    @RequestMapping(value = "/sayHello")
    @GlobalTransactional
    public ApiResult dubboSayHello(){
        log.info("全局事务Xid：" + RootContext.getXID());
        String sayHello = demoService.sayHello("sayHello");
        if(isTrue){
            throw new RuntimeException("模拟事务回滚，全局事务Xid：" + RootContext.getXID());
        }
        return ApiResult.success(sayHello);
    }

    //监听nacos配置文件的变化
    @NacosConfigListener(
            dataId = "nacos_config",
            groupId = "nacos_group",
            timeout = 500
    )
    public void onChange(String newContent) throws Exception {
        System.out.println("onChange : " + newContent);
    }

    @NacosConfigListener(
            dataId = "nacos_config2",
            groupId = "nacos_group",
            timeout = 500
    )
    public void onChange2(String newContent) throws Exception {
        System.out.println("onChange : " + newContent);
    }

}
