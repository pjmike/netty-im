# netty-im

一个基于Netty实现的简易即时通讯IM系统

## 实现功能

- 登录认证
- 单聊
- 群聊
- 心跳检测
- 客户端重连
- 监控系统的连接数等


## 快速启动
- 服务端启动：执行ServerApplication
- 客户端启动：执行ClientApplication

```java
22:40:42.765 [main] INFO com.pjmike.client.ClientApplication - 启动客户端成功,开启控制台线程...
Usage: <cmd> [options]
	<login>	[启动登录模式]
	<chat>	[启动单聊模式]
	<createGroup>	[创建群聊]
	<groupChat>	[发送群消息]
22:40:43.158 [nioEventLoopGroup-2-1] INFO com.pjmike.client.NettyClient - 连接服务端成功
```
