package com.pjmike.server.handler;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>统计并展示当前系统连接数</p>
 *
 * <p>
 *  简单利用Metrics库，它是一个Java库，可以对系统进行监控，统计一些系统的性能指标。
 * </p>
 *
 * @author: pjmike
 * @create: 2019/11/20
 */
@ChannelHandler.Sharable
public class MetricHandler extends ChannelDuplexHandler {
    private AtomicLong totalConnectionNumber = new AtomicLong();
    {
        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("totalConnectionNumber", (Gauge<Long>) () -> totalConnectionNumber.longValue());

        //Console日志定时输出
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(5, TimeUnit.SECONDS);

        //JMX实时展示
        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
    }
    /**
     * channelActive 当Channel已经连接/绑定并且就绪时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        totalConnectionNumber.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        totalConnectionNumber.decrementAndGet();
        super.channelInactive(ctx);
    }
}
