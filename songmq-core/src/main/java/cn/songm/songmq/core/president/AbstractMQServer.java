package cn.songm.songmq.core.president;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * Broker 服务
 * @author zhangsong
 *
 */
public abstract class AbstractMQServer implements MQServer {

    //private static final Logger LOG = LoggerFactory
    //        .getLogger(AbstractMQServer.class);

    protected String serverIp;
    protected Integer serverPort;

    protected SocketAddress serverIpAddr;
    protected ServerBootstrap bootstrap;
    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workerGroup;

    public AbstractMQServer(String ip, int port) {
        this.serverIp = ip;
        this.serverPort = port;
        
        serverIpAddr = new InetSocketAddress(ip, port);
        bootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
    }

    public String getServerIp() {
        return serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public SocketAddress getServerIpAddr() {
        return serverIpAddr;
    }

    public ServerBootstrap getBootstrap() {
        return bootstrap;
    }

    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }
}
