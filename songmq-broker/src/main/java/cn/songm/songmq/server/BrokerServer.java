package cn.songm.songmq.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.songm.songmq.broker.BrokerChInitializer;
import cn.songm.songmq.broker.MessageBrokerDispatcher;
import cn.songm.songmq.core.MQueueConfig;
import cn.songm.songmq.core.president.AbstractMQServer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Broker 服务
 * 
 * @author zhangsong
 *
 */
public class BrokerServer extends AbstractMQServer {

    private static final Logger LOG = LoggerFactory
            .getLogger(BrokerServer.class);

    private BrokerChInitializer chInitializer;

    public BrokerServer() {
        super(MQueueConfig.getInstance().getBrokerIp(),
                MQueueConfig.getInstance().getBrokerTcpPort());
        this.init();
    }

    private void init() {
        chInitializer = new BrokerChInitializer(new MessageBrokerDispatcher());
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_SNDBUF, 65535)
                .option(ChannelOption.SO_RCVBUF, 65535)
                .handler(new LoggingHandler(LogLevel.INFO))
                .localAddress(serverIpAddr).childHandler(chInitializer);
    }

    @Override
    public void startup() {
        new Thread(this, "SongMQBrokerTCP").start();
    }

    @Override
    public void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        chInitializer.shutdownGracefully();
    }

    @Override
    public void run() {
        try {
            ChannelFuture sync = bootstrap.bind().sync();
            LOG.info("SongMQ broker TCP start: {}:{}", serverIp, serverPort);
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            this.shutdown();
            LOG.info("SongMQ broker TCP shutdown: {}:{}", serverIp, serverPort);
        }
    }
}
