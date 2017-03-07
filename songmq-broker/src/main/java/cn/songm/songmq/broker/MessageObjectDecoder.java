package cn.songm.songmq.broker;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.songm.songmq.broker.serialize.KryoCodecUtil;
import cn.songm.songmq.broker.serialize.KryoPoolFactory;
import cn.songm.songmq.broker.serialize.MessageCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageObjectDecoder extends ByteToMessageDecoder {

    final public static int MESSAGE_LENGTH = MessageCodecUtil.MESSAGE_LENGTH;
    private MessageCodecUtil util;

    public MessageObjectDecoder() {
        this.util = new KryoCodecUtil(KryoPoolFactory.getKryoPoolInstance());
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
            List<Object> out) {
        if (in.readableBytes() < MessageObjectDecoder.MESSAGE_LENGTH) {
            return;
        }

        in.markReaderIndex();
        int messageLength = in.readInt();

        if (messageLength < 0) {
            ctx.close();
        }

        if (in.readableBytes() < messageLength) {
            in.resetReaderIndex();
            return;
        } else {
            byte[] messageBody = new byte[messageLength];
            in.readBytes(messageBody);

            try {
                Object obj = util.decode(messageBody);
                out.add(obj);
            } catch (IOException ex) {
                Logger.getLogger(MessageObjectDecoder.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
