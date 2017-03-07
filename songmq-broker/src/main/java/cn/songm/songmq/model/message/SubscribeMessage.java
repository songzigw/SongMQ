package cn.songm.songmq.model.message;

import java.io.Serializable;

public class SubscribeMessage extends BaseMessage implements Serializable {

    private static final long serialVersionUID = -1832092465474391089L;

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "SubscribeMessage [topic=" + topic + ", toString()="
                + super.toString() + "]";
    }

}
