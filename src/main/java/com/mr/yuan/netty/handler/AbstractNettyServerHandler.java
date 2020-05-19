package com.mr.yuan.netty.handler;

import com.mr.yuan.netty.common.DefaultChannelDeposit;
import com.mr.yuan.netty.common.pool.ByteBufPoolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.pool2.impl.GenericObjectPool;

public abstract class AbstractNettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 唯一标识
     */
    protected String label;

    /**
     * 客户端连接寄存
     */
    protected DefaultChannelDeposit deposit = DefaultChannelDeposit.getInstance();

    /**
     * 数据缓冲池
     */
    protected ByteBuf byteBuf;

    /**
     * 缓冲对象 byteBuf 对象池
     */
    protected static GenericObjectPool<ByteBuf> pool;

    /**
     * 连接池对象只启动时候加载
     */
    static {
        pool = new GenericObjectPool<>(new ByteBufPoolFactory());
    }

    /**
     * socket通道打开时触发
     * @param ctx
     */
    protected abstract void channelOpen(ChannelHandlerContext ctx);

    /**
     * socket通道关闭时触发
     * @param ctx
     */
    protected abstract void channelClose(ChannelHandlerContext ctx);

    /**
     * socket通道有可读数据时触发
     * @param ctx
     * @param msg
     */
    protected abstract void channelR(ChannelHandlerContext ctx ,Object msg);

    /**
     * socket通道可读数据读取完成时触发
     * @param ctx
     */
    protected abstract void channelRComplete(ChannelHandlerContext ctx);

    /**
     * socket通道异常时触发
     * @param ctx
     * @param cause
     */
    protected abstract void channelException(ChannelHandlerContext ctx ,Throwable cause);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelOpen(ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelClose(ctx);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        channelR(ctx , msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        channelRComplete(ctx);
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channelException(ctx , cause);
        super.exceptionCaught(ctx, cause);
    }
}
