package com.mr.yuan.web;

import com.mr.yuan.netty.common.DefaultChannelDeposit;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/")
public class DictateController {

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "label", value = "数据下行;客户端标识", dataType = "String",required = true),
            @ApiImplicitParam(paramType = "query", name = "content", value = "数据下行内容", dataType = "String")})
    @ApiOperation(value="数据下行，指令透传", notes="")
    @GetMapping(value="/cmd")
    public String cmd(String label , String content){
        Channel channel = DefaultChannelDeposit.getInstance().getChannel(label);
        if(channel == null || !channel.isActive())
            return "false";
        try {
            ByteBuf buf = Unpooled.wrappedBuffer(content.getBytes("utf-8"));
            channel.writeAndFlush(buf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "true";
    }

    /**
     * 响应式数据下行，需要做异步转同步操作；需要数据协议支持，传入唯一标识
     */

}
