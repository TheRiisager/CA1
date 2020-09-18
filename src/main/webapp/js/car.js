//  Author: Benjamin Choleva

// the sortTable() function sorts alphabetically. Used for the strings in the CarTable.
function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("carTable");
    switching = true;
    dir = "asc";
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];

            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {

            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount++;
        } else {
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }

        }
    }
}

// The sortTable2 sorts numerically. Used for the integers in the carTable.
function sortTable2(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("carTable");
    switching = true;
    dir = "asc";

    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];

            if (dir == "asc") {
                if (x.innerHTML - 1 > y.innerHTML - 1) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML - 1 < y.innerHTML - 1) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {

            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount++;
        } else {
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }

        }
    }
}
// the enter() function makes it possible to press enter instead of clicking the button, not an "essential" feature, but nice to have.
function enter() {
    $("#filterPrice").keypress(function (event) {
        if (event.keyCode === 13 && document.getElementById("filterPrice") !== null) {
            filterPrice();
        }
    });



    $("#filterYear").keypress(function (event) {
        if (event.keyCode === 13 && document.getElementById("filterYear") !== null) {
            filterYear();
        }
    });

    $("#filterMake").keypress(function (event) {
        if (event.keyCode === 13 && document.getElementById("filterMake") !== null) {
            filterMake();
        }
    });
}

$(document).ready(function () {
    mapCarsToHtml(cars);
    document.getElementById('tBody1').innerHTML = myHtmlArray.join('');
});

var cars = [];
fetch("api/car/all").then((res) => res.json().then(data => {
        var temp = data.map((car) => {
            cars.push({id: car.id, year: car.year, make: car.make, model: car.model, owner: car.owner, price: car.price});
        });
    })
);

let insertList = function (event) {
    let result = fetch("api/car/all").then(res => res.json()).then(data => {
        let prepData = data.map(formatToTR);
        document.getElementById("tBody1").innerHTML = prepData.join("");
    });
};
insertList();

let formatToTR = function (item) {
    return "<tr><td>" + item.id + "</td><td>" + item.year + "</td> <td>" + item.make + "</td> <td>" + item.model + "</td> <td>" + item.owner + "</td> <td>" + item.price + "</td></tr>";
};

let myHtmlArray = [];

function mapCarsToHtml(cars) {
    if (myHtmlArray.length === 0) {
        cars.map((car) => {
            myHtmlArray.push(`<tr><td>${car.id}</td>
                        <td>${car.year}<td>${car.make}</td><td>${car.model}</td>
                        <td>${car.owner}</td><td>${car.price}</td></tr>`);
        });
    } else {
        myHtmlArray = [];
        cars.map((car) => {
            myHtmlArray.push(`<tr><td>${car.id}</td>
                        <td>${car.year}</td><td>${car.make}</td><td>${car.model}</td>
                        <td>${car.owner}</td><td>${car.price}</td></tr>`);
        });
    }
}


// the filter functions. Some basic filtering. More or less self-explanatory what they do.
function filterPrice() {

    const filteredCars = cars.filter(car => car.price < document.getElementById('filterPrice').value);
    mapCarsToHtml(filteredCars);
    document.getElementById('tBody1').innerHTML = myHtmlArray.join('');
}

function filterYear() {

    const filteredCars = cars.filter(car => car.year > document.getElementById('filterYear').value);
    mapCarsToHtml(filteredCars);
    document.getElementById('tBody1').innerHTML = myHtmlArray.join('');
}

function filterMake() {

    const filteredCars = cars.filter(car => car.make.toLowerCase() === document.getElementById('filterMake').value.toLowerCase());
    mapCarsToHtml(filteredCars);
    document.getElementById('tBody1').innerHTML = myHtmlArray.join('');
}

function showAll() {
    insertList();

}




                    