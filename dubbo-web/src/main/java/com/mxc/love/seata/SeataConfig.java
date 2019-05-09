package com.mxc.love.seata;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 马秀成
 * @date 2019/5/5
 * @jdk.version 1.8
 * @desc
 */
@Configuration
public class SeataConfig {

    /**
     * init global transaction scanner
     *
     * @Return: GlobalTransactionScanner
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner("web-gts-fescar-example", "my_test_tx_group");
    }

}
