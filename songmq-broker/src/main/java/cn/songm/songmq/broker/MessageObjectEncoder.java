package cn.songm.songmq.broker;

import cn.songm.songmq.broker.serialize.KryoCodecUtil;
import cn.songm.songmq.broker.serialize.KryoPoolFactory;
import cn.songm.songmq.broker.serialize.MessageCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageObjectEncoder extends MessageToByteEncoder<Object> {

    private MessageCodecUtil util;

    public MessageObjectEncoder() {
        this.util = new KryoCodecUtil(KryoPoolFactory.getKryoPoolInstance());
    }

    protected void encode(final ChannelHandlerContext ctx, final Object msg,
            final ByteBuf out) throws Exception {
        util.encode(out, msg);
    }
}
