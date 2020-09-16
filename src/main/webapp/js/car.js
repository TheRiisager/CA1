//  Author: Benjamin Choleva
  
  
            function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("carTable");
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc";
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */

                
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++;
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
      
    }
  }
}

            function sortTable2(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("carTable");
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc";
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */

                
      if (dir == "asc") {
        if (x.innerHTML-1 > y.innerHTML-1) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML-1 < y.innerHTML-1) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++;
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
      
    }
  }
}


                function enter() {
                  $("#filterPrice").keypress(function(event) { 
            if (event.keyCode === 13 && document.getElementById("filterPrice") !== null)  { 

                filterPrice();
             
            }
             }); 
       
            
            
              $("#filterYear").keypress(function(event) { 
            if (event.keyCode === 13 && document.getElementById("filterYear") !== null)  { 

                filterYear();
             
            }
             }); 
            
                 $("#filterMake").keypress(function(event) { 
            if (event.keyCode === 13 && document.getElementById("filterMake") !== null)  { 

                filterMake();
             
            }
             }); 
             }

            
            $(document).ready(function () {
                mapCarsToHtml(cars);
                document.getElementById('tBody1').innerHTML = myHtmlArray.join('');
            });

            var cars = [];
            fetch("api/car/all").then((res) => res.json()
                        .then(data => {
                            var temp = data.map((car) => {
                                cars.push({id: car.id, year: car.year, make: car.make, model: car.model, owner: car.owner, price: car.price});
                            });
                        })
            );
    
    let insertList = function(event){
    let result = fetch("api/car/all")
   .then(res => res.json()) //in flow1, just do it
   .then(data => {
        // Inside this callback, and only here, the response data is available
        let prepData = data.map(formatToTR);
        document.getElementById("tBody1").innerHTML = prepData.join("");
    });
};
  insertList();

let formatToTR = function(item){
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
            

            function fetchFromApiById() {
                //      let id = document.getElementById("myInput").value;
                
                fetch(`api/car/${id}`).then((res) => res.json()
                            .then(data => (document.getElementById("ul3").innerHTML = `<table class="table">
            <tr><td>${data.id}</td>
            <td>${data.year}</td>
            <td>${data.make}</td> 
            <td>${data.model}</td>                       
            <td>${data.owner}</td>
            <td>${data.price}</td></tr></table>`)));
            }

            function fetchFromAPIAll() {
                fetch("api/car/all")
                        .then((res) => res.json()
                                    .then(data => {
                                        var temp = data.map((car) => {
                                            return `<table class="table"><tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${data.make}</td> 
                    <td>${car.model}</td>                    
                    td>${car.owner}</td>
                    <td>${car.price}</td>
                        </tr></table>`;
                                        });
                                        document.getElementById("ul1").innerHTML = temp.join('');
                                    })
                        );
            }
                    