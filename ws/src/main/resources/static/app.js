let ws;
let id = Math.random();

function connect() {
    ws = new WebSocket("ws://localhost:8080/hello?id=" + id)
    ws.onopen = function (evt) {
        document.getElementById("msg").innerHTML = 'Status: connected';
    };

    ws.onmessage = function (evt) {
        document.getElementById("msg").innerHTML = 'Status: getMsg: ' + evt.data;
    };

    ws.onclose = function (evt) {
        document.getElementById("msg").innerHTML = 'Status: closed';
    };
}

function cSend() {
    ws.send("Hello, Server!");
}

function sSend() {
    let request = new XMLHttpRequest();
    request.open("GET", document.location.origin + "/websocket/send?id=" + id, true);
    request.send();
    request.onreadystatechange = function () {
    }
}

function disconnect() {
    let request = new XMLHttpRequest();
    request.open("GET", document.location.origin + "/websocket/disconnect?id=" + id, true);
    request.send();
    request.onreadystatechange = function () {
    }
}