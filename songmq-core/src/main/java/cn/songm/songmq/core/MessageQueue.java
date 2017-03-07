package cn.songm.songmq.core;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 消息队列
 * 
 * @author zhangsong
 * @since 0.1, 2017-02-18
 * @version 0.1
 *
 */
public abstract class MessageQueue {

    private Topic topic;
    private ConcurrentLinkedQueue<byte[]> queue;

    public MessageQueue(Topic topic) {
        this.topic = topic;
        queue = new ConcurrentLinkedQueue<byte[]>();
    }

    public boolean pushMessage(byte[] payload) {
        return queue.offer(payload);
    }

    public boolean pushMessage(List<byte[]> payloads) {
        return queue.addAll(payloads);
    }

    public byte[] getMessage() {
        return queue.poll();
    }

    public Topic getTopic() {
        return topic;
    }
}
