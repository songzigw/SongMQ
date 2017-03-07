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
package cn.songm.songmq.model;

/**
 * 消息类型
 * 
 * @author zhangsong
 * @since 0.1, 2017-02-18
 * @version 0.1
 *
 */
public enum MessageType {

    SUBSCRIBE(1),
    UNSUBSCRIBE(2),
    QMESSAGE(3),
    PRODUCERACK(4),
    CONSUMERACK(5);

    private int messageType;

    private MessageType(int messageType) {
        this.messageType = messageType;
    }

    int getMessageType() {
        return messageType;
    }
}
