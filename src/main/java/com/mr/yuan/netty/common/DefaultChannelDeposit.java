package com.mr.yuan.netty.common;

public class DefaultChannelDeposit extends AbstractChannelDeposit {

    private DefaultChannelDeposit(){}

    private static DefaultChannelDeposit instance;

    public static DefaultChannelDeposit getInstance() {
        if (instance == null) {
            synchronized (DefaultChannelDeposit.class) {
                if (instance == null) {
                    instance = new DefaultChannelDeposit();
                }
            }
        }
        return instance;
    }

    @Override
    protected void clear() {
        super.clear();
    }
}
