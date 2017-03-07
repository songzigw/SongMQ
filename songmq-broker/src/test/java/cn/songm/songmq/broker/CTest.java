package cn.songm.songmq.broker;

import cn.songm.songmq.client.Client;
import cn.songm.songmq.client.MQClient;

public class CTest {

    public static void main(String[] args) throws Exception {
         MQClient.init("127.0.0.1", 18888);
         Client c = MQClient.getInstance();
         c.connect();
    }
}
