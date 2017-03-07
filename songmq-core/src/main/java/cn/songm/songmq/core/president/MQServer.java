package cn.songm.songmq.core.president;

public interface MQServer extends Runnable {

    void start();

    void shutdown();
}
