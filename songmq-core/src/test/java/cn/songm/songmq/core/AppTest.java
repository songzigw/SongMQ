package cn.songm.songmq.core;

public class AppTest {

    public static void main(String[] args) {
	// 创建主题t1
	Topic t1 = new Topic();
	t1.setName("/topic/t1");
	t1.setModel(MQueueModel.PUB_SUB);
	// 创建主题t2
	Topic t2 = new Topic();
	t2.setName("/topic/t2");
	t2.setModel(MQueueModel.PUB_SUB);
	
	MQueueManager mqm = MQueueManager.getInstance();
	MessageQueue mq1 = mqm.createMQ(t1);
	MessageQueue mq2 = mqm.createMQ(t2);
    }

}
