let stomp;
let id = Math.random();

function connect() {
    let ws = new WebSocket("ws://localhost:8080/WSConnection?id=" + id);
    stomp = Stomp.over(ws);

    stomp.connect({}, function () {
        document.getElementById("msg").innerHTML = 'Status: connected';
        stomp.subscribe('/WS/subscribe/hello', function (evt) {
            document.getElementById("msg").innerHTML = 'Status: getMsg: ' + evt.body;
        });
    });
}

function cPublish() {
    stomp.send("/WS/publish/hello", {}, "Hello, server, my name is " + id);
}

function disconnect() {
    stomp.disconnect();
    document.getElementById("msg").innerHTML = 'Status: closed';
}