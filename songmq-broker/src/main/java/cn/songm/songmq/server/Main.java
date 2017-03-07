package cn.songm.songmq.server;

/**
 * 程序入口
 * 
 * @author zhangsong
 *
 */
public class Main {

    public static void main(String[] args) {
        new MQContainer().start();
    }
}
