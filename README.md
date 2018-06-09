# Server Push

# Purpose

- Compare Server Push technologies
- Solve Load Balance questions in Server Push

# Compare 

### Short Polling

- Easy
- HTTP basic, load balance compatibility good
- Make a lot of request, including lot of HTTP header (wasteful)
- No need to manage connections
- Simplex

### Long Polling 

- Easy
- HTTP basic, load balance compatibility good
- Make one request, but server will hold connection (less wasteful)
- Need to manage connections
- Simplex

### Sse

- Normal
- HTTP basic, load balance compatibility good
- Server push (saving)
- Auto manage connections
- Simplex

### WS

- Hard
- Handshake by HTTP, next ws tunnel, **load balance compatibility bad**
- Server push, without HTTP header (more saving)
- Need manage to connections
- Duplex
- It is the combination of low latency, high frequency and high volume that make the best case for the use WebSocket

## Sse

### Define

Server-sent Event

### Procedure

- Generated SseEmitter
- Register SseEmitter Event
- Save SseEmitter for other programs
- Return SseEmitter in Controller

### Ref

[Sse Spring](https://www.jeejava.com/server-sent-events-spring-push-notifications/)

## WS

### Define

WebSocket

### Req & Res for begin

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

### Spring extensions

- @EnableWebSocket，启用 WebSocket
- WebSocketConfigurer，配置 WebSocket
- WebSocketHandler，处理 WebSocket 传输的信息
- HandshakeInterceptor，配置 WebSocket 连接建立之前、之后的处理
- ServletServerContainerFactoryBean，配置 WebSocket 信息大小等限制

### STOMP

#### Procedure

- 建立 WS
- 建立 STOMP 客户端
- 客户端订阅服务端推送主题
- 客户端向服务端订阅的主题发布消息
- 服务端订阅客户端推送主题
- 服务端向客户端订阅的主题发布消息

#### Usage

- WebSocketMessageBrokerConfigurer，STOMP 配置
- @EnableWebSocketMessageBroker，启用 STOMP
- @MessageMapping，客户端发布消息，服务端订阅主题
- @SendTo，客户端订阅主题，服务端发布消息

### Ref

[Spring WebSocket doc](https://docs.spring.io/spring/docs/5.0.6.RELEASE/spring-framework-reference/web.html#websocket)

[Server Push comparision](https://blog.stanko.io/do-you-really-need-websockets-343aed40aa9b)

[Spring WebSocket guide](http://spring.io/guides/gs/messaging-stomp-websocket)

## Server Push Load Balance questions

### Bug

前提：服务器A、服务器B、网页客户端A、手机客户端B

假设请求是扫码登录请求

- 进入用户登录页面，服务器 A 和网页客户端 A 建立 Server Push 连接（WS、SSE）
- 用户手机扫码，确认登录，发出登录请求，手机客户端 B 向服务器 B 发出登录请求
- 服务器 B 确认登录请求，由于 Server Push 连接是在服务器 A 上，服务器 B 无法推送消息到网页客户端 A  

### Solution

TDB