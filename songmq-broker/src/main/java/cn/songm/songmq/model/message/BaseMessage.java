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
package cn.songm.songmq.model.message;

import cn.songm.common.utils.StringUtils;
import cn.songm.songmq.core.president.MQMessage;

/**
 * 抽象消息类
 * 
 * @author zhangsong
 * @since 0.1, 2017-02-18
 * @version 0.1
 *
 */
public abstract class BaseMessage implements MQMessage {

    private static final long serialVersionUID = 1316673300348117071L;

    private String msgId;
    
    public BaseMessage() {
        this.msgId = StringUtils.get32UUID();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((msgId == null) ? 0 : msgId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseMessage other = (BaseMessage) obj;
        if (msgId == null) {
            if (other.msgId != null) return false;
        } else if (!msgId.equals(other.msgId)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "BaseMessage [msgId=" + msgId + "]";
    }

}
