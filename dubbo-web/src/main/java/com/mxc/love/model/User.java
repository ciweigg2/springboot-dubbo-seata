package com.mxc.love.model;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@NacosConfigurationProperties(dataId = "nacos_config2" ,groupId = "nacos_group" ,autoRefreshed = true)
@Component
public class User {

    private String name;

}
