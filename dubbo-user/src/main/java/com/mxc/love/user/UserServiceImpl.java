/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mxc.love.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mxc.love.mybatis.entity.User;
import com.mxc.love.mybatis.mapper.UserMapper;
import com.mxc.love.service.LiveService;
import com.mxc.love.service.UserService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Default {@link UserService}
 *
 * @see UserService
 * @since 1.0.0
 */
@Service(version = "${demo.service.version}")
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Reference(version = "${demo.service.version}")
    private LiveService liveService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String sayHello(String name) {
        log.info("全局事务Xid：" + RootContext.getXID());
        userMapper.update(
                new User().setUserName("测试分布式事务"),
                new LambdaQueryWrapper<User>().eq(User::getUserName, "haha")
        );
        String say = liveService.sayName();
        return String.format(serviceName, name + "：" +say);
    }
}