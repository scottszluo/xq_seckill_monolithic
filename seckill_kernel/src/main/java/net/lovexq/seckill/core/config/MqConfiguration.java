package net.lovexq.seckill.core.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by LuPindong on 2017-2-15.
 */
@Configuration
@EnableJms
public class MqConfiguration {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("LianJiaCrawler.Queue");
    }
}