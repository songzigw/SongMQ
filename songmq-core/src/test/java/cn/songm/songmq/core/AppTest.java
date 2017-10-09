package cn.songm.songmq.core;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.songm.songmq.core.president.MQMessage;

public class AppTest {

    @Test
    public void testApp() {
        String eventId = "abc";
        CallbackManager callback = CallbackManager.getInstance();
        CallbackInvoker invoker = new CallbackInvoker(eventId);
        invoker.join(new Message());
        callback.put(invoker);
        
        callback.notify(eventId, "123456");
        
        Object obj = callback.getResult(eventId, 10000, TimeUnit.MILLISECONDS);
        System.out.println(obj);
    }
    
}

class Message implements CallbackListener, MQMessage {

    private static final long serialVersionUID = -2779236373009413444L;

    @Override
    public void onCallBack(Object t) {
        System.out.println(t);
    }

}
