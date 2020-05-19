package com.mr.yuan.netty.common.pool;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ByteBufPoolFactory implements PooledObjectFactory<ByteBuf> {

    @Override
    public PooledObject<ByteBuf> makeObject() throws Exception {
        return new DefaultPooledObject<>(Unpooled.buffer(1024 * 10));
    }

    @Override
    public void destroyObject(PooledObject<ByteBuf> p) throws Exception {
        p.getObject().clear();
    }

    @Override
    public boolean validateObject(PooledObject<ByteBuf> p) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<ByteBuf> p) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<ByteBuf> p) throws Exception {

    }
}
