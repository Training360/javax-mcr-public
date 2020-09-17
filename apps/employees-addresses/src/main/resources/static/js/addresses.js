let list = document.querySelector("#addresses-ul");

window.onload = function() {

    let evtSource = new EventSource("api/addresses/stream");
    evtSource.addEventListener("event",
        function(event) {
            var data = JSON.parse(event.data);
            appendRequest(data.name)
        });

}

function appendRequest(name) {
    list.innerHTML = `<li>Request to: ${name}</li>` + list.innerHTML;
}