package com.mxc.love.live;

import com.mxc.love.mybatis.entity.Live;
import com.mxc.love.mybatis.mapper.LiveMapper;
import com.mxc.love.service.LiveService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${demo.service.version}")
@Slf4j
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveMapper liveMapper;

    @Override
    public String sayName() {
        log.info("全局事务Xid：" + RootContext.getXID());
        log.info("隐示传参：" + RpcContext.getContext().getAttachment("CRT_MEMBER_ID"));
        liveMapper.insert(new Live().setLiveName("卡莎直播呀"));
        return "小马大神呀";
    }

}
