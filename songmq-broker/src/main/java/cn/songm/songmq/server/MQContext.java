package cn.songm.songmq.server;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import cn.songm.songmq.core.president.MQServer;

public class MQContext implements ApplicationContextAware, Context {

    private AbstractApplicationContext context;

    private List<MQServer> mqServers;

    @Override
    public AbstractApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = (AbstractApplicationContext) context;
    }

    public List<MQServer> getMqServers() {
        return mqServers;
    }

    public void setMqServers(List<MQServer> mqServers) {
        this.mqServers = mqServers;
    }

}
