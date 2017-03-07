package cn.songm.songmq.server;

import org.springframework.context.support.AbstractApplicationContext;

/**
 * 应用上下文环境
 * @author zhangsong
 *
 * @param <T>
 */
public interface Context {

    /**
     * 获取上下文环境
     * @return
     */
    AbstractApplicationContext getContext();
}
