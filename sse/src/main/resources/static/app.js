let id = Math.random();

function connect() {
    const eventSource = new EventSource("http://localhost:8080/sse/notification?id=" + id);
    eventSource.onmessage = e => {
        document.getElementById("msg").innerHTML = 'Receive msg: ' + e.data;
    };
    eventSource.onopen = () => {
        document.getElementById("msg").innerHTML = 'Open Sse connection';
    };
}