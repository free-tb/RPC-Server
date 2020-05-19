package com.mr.yuan.netty.common;

import io.netty.channel.Channel;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractChannelDeposit implements ChannelDeposit {

    private ConcurrentHashMap<String, Channel> channels;

    protected AbstractChannelDeposit() {
        this.channels = new ConcurrentHashMap<>();
    }

    @Override
    public Channel registerChannel(String k, Channel v) {
        return channels.put(k, v);
    }

    @Override
    public Channel unRegisterChannel(String k) {
        if (channels.containsKey(k))
            return channels.remove(k);
        return null;
    }

    @Override
    public Channel getChannel(String k) {
        return channels.get(k);
    }

    protected void clear() {
        channels.clear();
    }
}