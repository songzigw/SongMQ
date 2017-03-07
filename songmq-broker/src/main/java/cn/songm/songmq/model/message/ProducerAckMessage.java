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

/**
 * 生产者应答消息
 * 
 * @author zhangsong
 * @since 0.1, 2017-02-18
 * @version 0.1
 *
 */
public class ProducerAckMessage extends BaseMessage {

    private static final long serialVersionUID = 2207206655238806652L;

    private String ack;
    private int status;

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProducerAckMessage [ack=" + ack + ", status=" + status
                + ", toString()=" + super.toString() + "]";
    }

}
