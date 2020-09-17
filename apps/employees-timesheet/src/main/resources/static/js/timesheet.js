let table = document.querySelector("#employees-table");

window.onload = function() {
    loadEmployees();

    let evtSource = new EventSource("api/events/stream");
    evtSource.addEventListener("event",
        function(event) {
            loadEmployees();
        });

}

function loadEmployees() {
    fetch('api/employees')
        .then(response => response.json())
        .then(employees => printEmployees(employees));
}

function printEmployees(employees) {
    table.innerHTML = "";
    for (let employee of employees) {
        print(employee);
    }
}

function print(employee) {
    let content = `
          <tr>
          <td>${employee.id}</td>
          <td>${employee.name}</td>
          <td>${employee.hours}</td>
          </tr>
        `

    table.insertAdjacentHTML( 'afterbegin', content);
}