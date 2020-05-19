package com.mr.yuan.netty.handler;

import com.mr.yuan.netty.common.DefaultChannelDeposit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

@Slf4j
public class DefaultAdaptorHandler extends AbstractNettyServerHandler{

    public DefaultAdaptorHandler(){
        try {
            byteBuf = pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void channelOpen(ChannelHandlerContext ctx) {
        log.info(ctx.channel().id().toString());
    }

    @Override
    protected void channelClose(ChannelHandlerContext ctx) {
        deposit.unRegisterChannel(ctx.channel().id().toString());
        log.info(ctx.channel().id().toString());
    }

    @Override
    protected void channelR(ChannelHandlerContext ctx, Object msg) {
        byteBuf = (ByteBuf) msg;
        byte [] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        try {
            log.info(new String(bytes , "utf-8"));
            log.info(byteBuf.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void channelRComplete(ChannelHandlerContext ctx) {
        deposit.registerChannel(ctx.channel().id().toString(),ctx.channel());
        log.info(ctx.channel().id().toString());
    }

    @Override
    protected void channelException(ChannelHandlerContext ctx, Throwable cause) {
        deposit.unRegisterChannel(ctx.channel().id().toString());
        log.info(ctx.channel().id().toString());
    }

}
