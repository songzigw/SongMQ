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

import cn.songm.songmq.core.president.MQProtocol;
import cn.songm.songmq.model.message.BaseMessage;

/**
 * 业务逻辑消息基类
 * 
 * @author zhangsong
 * @since 0.1, 2017-02-18
 * @version 0.1
 *
 */
public abstract class BizMessage implements MQProtocol {

    private static final long serialVersionUID = -4830933054886805306L;

    /** 消息参数 */
    protected BaseMessage msgParams;
    /** 消息来源 */
    protected MessageSource msgSource;
    /** 消息类型 */
    protected MessageType msgType;

    public BizMessage(BaseMessage message) {
        this.msgParams = message;
    }
    
    public String getMsgId() {
        return msgParams.getMsgId();
    }

    public BaseMessage getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(BaseMessage msgParams) {
        this.msgParams = msgParams;
    }

    public MessageSource getMsgSource() {
        return msgSource;
    }

    public void setMsgSource(MessageSource msgSource) {
        this.msgSource = msgSource;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "BizMessage [msgParams=" + msgParams + ", msgSource=" + msgSource
                + ", msgType=" + msgType + "]";
    }
}
