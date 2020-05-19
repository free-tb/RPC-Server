package com.mr.yuan.netty.common;

import io.netty.channel.Channel;

public interface ChannelDeposit {

    Channel registerChannel(String k , Channel v);

    Channel unRegisterChannel(String k);

    Channel getChannel(String k);
}
