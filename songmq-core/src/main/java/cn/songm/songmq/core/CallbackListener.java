package cn.songm.songmq.core;

/**
 * 回调监听器
 * 
 * @author zhangsong
 *
 * @param <T>
 */
public interface CallbackListener<T> {

    void onCallBack(T t);

}
