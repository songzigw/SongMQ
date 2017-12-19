package cn.songm.songmq.server;

import java.util.Map;

import cn.songm.songmq.core.president.MQServer;

public class MQContainer implements Container {

    private static final String CONFIG = "classpath:songmq-broker.xml";

    private MQContext context;

    public MQContainer() {
        context = new MQContext(CONFIG);
        context.start();
    }

    @Override
    public void start() {
        Map<String, MQServer> beans = context.getBeansOfType(MQServer.class);
        for (MQServer ser : beans.values()) {
            ser.startup();
        }
    }

    @Override
    public void stop() {
        Map<String, MQServer> beans = context.getBeansOfType(MQServer.class);
        for (MQServer ser : beans.values()) {
            ser.shutdown();
        }
        context.stop();
        context.close();
    }

    @Override
    public MQContext getContext() {
        return context;
    }

}
