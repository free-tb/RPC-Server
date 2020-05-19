package com.mr.yuan.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

@Slf4j
public class DefaultNettyServerHandler extends AbstractNettyServerHandler {


    public DefaultNettyServerHandler(){
        try {
            super.byteBuf = super.pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void channelOpen(ChannelHandlerContext ctx) {
//        log.info("channelOpen : " + ctx.channel().id().toString());
    }

    @Override
    protected void channelClose(ChannelHandlerContext ctx) {
//        log.info("channelClose : " + ctx.channel().id().toString());
    }

    @Override
    protected void channelR(ChannelHandlerContext ctx, Object msg) {
        byteBuf = (ByteBuf) msg;
        byte [] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        log.info("pool  ".concat(pool.toString()));
    }

    @Override
    protected void channelRComplete(ChannelHandlerContext ctx) {

    }

    @Override
    protected void channelException(ChannelHandlerContext ctx, Throwable cause) {

    }
}
