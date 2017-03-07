package cn.songm.songmq.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息事件管理器
 * 
 * @author zhangsong
 *
 */
public class MessageEventManager {

    private static AtomicBoolean flag = new AtomicBoolean(false);

    private final Map<Topic, Set<MessageListener>> listeners;

    private MessageEventManager() {
        listeners = Collections
                .synchronizedMap(new HashMap<Topic, Set<MessageListener>>());
    }

    private static MessageEventManager manager;

    public static MessageEventManager getInstance() {
        if (manager == null && flag.compareAndSet(false, true)) {
            manager = new MessageEventManager();
        }
        if (manager == null) {
            getInstance();
        }
        return manager;
    }

    /**
     * 主题订阅
     * 
     * @param topic
     * @param listener
     */
    public void subscribe(Topic topic, MessageListener listener) {
        // 创建消息队列
        MessageQueue mq = MQueueManager.getInstance().createMQ(topic);
        topic = mq.getTopic();
        Set<MessageListener> set = listeners.get(mq.getTopic());
        if (set == null) {
            set = new HashSet<MessageListener>();
            listeners.put(topic, set);
        }
        set.add(listener);
    }

    /**
     * 取消主题订阅
     * 
     * @param topic
     * @param listener
     */
    public void unsubscribe(Topic topic, MessageListener listener) {
        Set<MessageListener> set = listeners.get(topic);
        if (set == null) {
            return;
        }
        set.remove(listener);
    }

    /**
     * 触发主题消息
     * 
     * @param event
     */
    public void trigger(MessageEvent event) {
        Topic topic = event.getTopic();
        Set<MessageListener> set = listeners.get(topic);
        if (set == null) return;

        switch (topic.getModel()) {
        case POINT_2_P:
            if (set.iterator().hasNext()) {
                set.iterator().next().onMessage(event);
            }
            break;
        case BROADCAST:
            for (MessageListener ler : set) {
                ler.onMessage(event);
            }
            break;
        default:
            break;
        }
    }
}
