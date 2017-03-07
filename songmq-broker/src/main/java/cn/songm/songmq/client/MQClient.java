package cn.songm.songmq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.songm.songmq.broker.BrokerChInitializer;
import cn.songm.songmq.model.message.QMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MQClient implements Client {

    private static final Logger LOG = LoggerFactory
            .getLogger(MQClient.class);
    
    private static MQClient instance;
    
    public static MQClient getInstance() {
        if (instance == null) {
            throw new NullPointerException("MQClient not init");
        }
        return instance;
    }

    public synchronized static MQClient init(String serverIp, int serverPort) {
        if (instance == null) {
            instance = new MQClient(serverIp, serverPort);
        }
        return instance;
    }
    
    private final String serverIp;
    private final int serverPort;

    private int connStatus;
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private BrokerChInitializer chHandler;
    private ChannelFuture future;
    
    private MQClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.init();
    }
    
    private void init() {
        this.group = new NioEventLoopGroup();
        this.connStatus = DISCONNECTED;
        this.chHandler = new BrokerChInitializer(new MessageClientHandler());
        
        bootstrap = new Bootstrap();
        bootstrap.group(this.group)
        .channel(NioSocketChannel.class)
        .handler(this.chHandler)
        .remoteAddress(serverIp, serverPort);
    }

    @Override
    public int getConnStatus() {
        return connStatus;
    }

    @Override
    public void connect() throws Exception {
        LOG.info("SongMQ broker connect: {}:{}", serverIp, serverPort);
        
        future = bootstrap.connect().sync();
        future.channel().closeFuture().sync();
    }

    @Override
    public void disconnect() throws Exception {
        if (future != null) {
            future.channel().close().syncUninterruptibly();
        }
        if (chHandler != null) {
            chHandler.shutdownGracefully();
        }
        if (group != null) {
            group.shutdownGracefully();
        }
    }

    @Override
    public void publish(QMessage message) {
        future.channel().writeAndFlush(message);
    }

    @Override
    public void subscribe(String topic) {
        
    }

    @Override
    public String getServerIp() {
        return serverIp;
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }
}
