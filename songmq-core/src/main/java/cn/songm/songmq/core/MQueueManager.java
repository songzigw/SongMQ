/**
 * Copyright (C) [2016] [zhangsong <songm.cn>].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.songm.songmq.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息队列管理器
 * 
 * @author zhangsong
 * @since 0.1, 2017-02-18
 * @version 0.1
 *
 */
public class MQueueManager {

    private static AtomicBoolean flag = new AtomicBoolean(false);

    private final Map<Topic, MessageQueue> mqMap;

    /** 单例消息队列管理器对象 */
    private static MQueueManager manager;

    private MQueueManager() {
        mqMap = Collections.synchronizedMap(new HashMap<Topic, MessageQueue>());
    }

    public static MQueueManager getInstance() {
        if (manager == null && flag.compareAndSet(false, true)) {
            manager = new MQueueManager();
        }
        if (manager == null) {
            getInstance();
        }
        return manager;
    }

    public MessageQueue createMQ(Topic topic) {
        if (mqMap.containsKey(topic)) {
            return mqMap.get(topic);
        }
        MessageQueue mq = null;
        switch (topic.getModel()) {
        case BROADCAST:
            mq = new BroadcastMessageQueue(topic);
            break;
        case POINT_2_P:
            mq = new Point2pMessageQueue(topic);
            break;
        default:
            throw new IllegalArgumentException("topic.getModel()");
        }
        return mqMap.put(topic, mq);
    }

}
