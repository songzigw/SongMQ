package cn.songm.songmq.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CallbackInvoker<T> {

    private static final int COUNT = 1;

    private final CountDownLatch countDown;
    private String requestId;
    private T messageResult;
    private Throwable reason;
    private List<CallbackListener<T>> listeners;

    {
        countDown = new CountDownLatch(COUNT);
        listeners = Collections
                .synchronizedList(new ArrayList<CallbackListener<T>>());
    }

    public CallbackInvoker(String requestId) {
        this.requestId = requestId;
    }

    private void publish() {
        for (CallbackListener<T> listener : listeners) {
            listener.onCallBack(messageResult);
        }
        CallbackManager.getInstance().remove(requestId);
    }

    public void setReason(Throwable reason) {
        this.reason = reason;
        publish();
        countDown.countDown();
    }

    public void setMessageResult(T messageResult) {
        this.messageResult = messageResult;
        publish();
        countDown.countDown();
    }

    public Object getMessageResult(long timeout, TimeUnit unit) {
        try {
            countDown.await(timeout, unit);
        } catch (InterruptedException e) {
            CallbackManager.getInstance().remove(requestId);
            throw new RuntimeException(e);
        }
        if (reason != null) {
            throw new RuntimeException(reason);
        }
        return messageResult;
    }

    public void join(CallbackListener<T> listener) {
        this.listeners.add(listener);
    }

    public String getRequestId() {
        return requestId;
    }

}
