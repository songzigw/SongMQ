package cn.songm.songmq.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 回调管理器
 * 
 * @author zhangsong
 *
 */
public class CallbackManager {

    private static AtomicBoolean flag;
    static {
        flag = new AtomicBoolean(false);
    }

    private final Map<String, CallbackInvoker<Object>> callbackMap;
    {
        callbackMap = Collections.synchronizedMap(
                new HashMap<String, CallbackInvoker<Object>>());
    }

    private static CallbackManager instance;

    private CallbackManager() {
    }

    public static CallbackManager getInstance() {
        if (instance == null && flag.compareAndSet(false, true)) {
            instance = new CallbackManager();
        }
        if (instance == null) {
            getInstance();
        }
        return instance;
    }
    
    public void put(String requestId, CallbackInvoker<Object> invoker) {
        callbackMap.put(requestId, invoker);
    }
    
    public void remove(String requestId) {
        callbackMap.remove(requestId);
    }
    
    public CallbackInvoker<Object> get(String requestId) {
        return callbackMap.get(requestId);
    }
    
    public void notify(String requestId, Object msgResult) {
        CallbackInvoker<Object> inv = callbackMap.get(requestId);
        if (inv != null) {
            inv.setMessageResult(msgResult);
        }
    }
}
