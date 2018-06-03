# WebSocket

## Req & Res for begin

```html
GET /spring-websocket-portfolio/portfolio HTTP/1.1
Host: localhost:8080
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: Uc9l9TMkWGbHFD2qnFHltg==
Sec-WebSocket-Protocol: v10.stomp, v11.stomp
Sec-WebSocket-Version: 13
Origin: http://localhost:8080
```

```html
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: 1qVdfYHU9hPOl4JYYNXF623Gzn0=
Sec-WebSocket-Protocol: v10.stomp
```

## Spring extensions

- @EnableWebSocket，启用 WebSocket
- WebSocketConfigurer，配置 WebSocket
- WebSocketHandler，处理 WebSocket 传输的信息
- HandshakeInterceptor，配置 WebSocket 连接建立之前、之后的处理
- ServletServerContainerFactoryBean，配置 WebSocket 信息大小等限制

## ref

[Spring WebSocket doc](https://docs.spring.io/spring/docs/5.0.6.RELEASE/spring-framework-reference/web.html#websocket)

