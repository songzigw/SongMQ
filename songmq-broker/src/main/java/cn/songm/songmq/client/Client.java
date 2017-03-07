package cn.songm.songmq.client;

import cn.songm.songmq.model.message.QMessage;

public interface Client {

    /** 连接断开 */
    public static final int DISCONNECTED = 0;
    /** 连接上了 */
    public static final int CONNECTED = 1;
    /** 正在连接 */
    public static final int CONNECTING = 2;

    /**
     * 获取服务器IP
     * @return
     */
    public String getServerIp();
    
    /**
     * 获取服务端口
     * @return
     */
    public int getServerPort();
    
    /**
     * 获取连接状态
     * @return
     */
    public int getConnStatus();

    /**
     * 开始连接
     * @throws Exception
     */
    public void connect() throws Exception;

    /**
     * 断开连接
     * @throws Exception
     */
    public void disconnect() throws Exception;

    /**
     * 发布消息
     * @param message
     */
    public void publish(QMessage message);
    
    /**
     * 订阅主题
     * @param topic
     */
    public void subscribe(String topic);
}
