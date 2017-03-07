package cn.songm.songmq.model.message;

import java.io.Serializable;

public class UnSubscribeMessage extends BaseMessage implements Serializable {

    private static final long serialVersionUID = 1513374294926924815L;

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "UnSubscribeMessage [topic=" + topic + ", toString()="
                + super.toString() + "]";
    }
}
