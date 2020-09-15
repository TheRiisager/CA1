
let header = "<tr><th>id</th><th>type</th></tr>";

let insertList = function(event){
    let result = fetch("api/jokes/all")
   .then(res => res.json()) //in flow1, just do it
   .then(data => {
        // Inside this callback, and only here, the response data is available
        let prepData = data.map(formatToTR);
        document.getElementById("jokeTable").innerHTML = header + prepData.join("");
    });
};

let formatToTR = function(item){
    return "<tr><td>" + item.id + "</td><td>" + item.type + "</td></tr>"; 
};

let getJokeByID = function(event){
    event.preventDefault();
    let id = document.getElementById("input");
    
    let result = fetch("api/jokes/all")
   .then(res => res.json()) //in flow1, just do it
   .then(data => {
        // Inside this callback, and only here, the response data is available
        document.getElementById("jokeOut").innerText = data.story;
    });
};

let getRandomJoke = function(){
    let result = fetch("api/jokes/random")
   .then(res => res.json()) //in flow1, just do it
   .then(data => {
        // Inside this callback, and only here, the response data is available
        document.getElementById("randomOut").innerText = data.story;
    });
}

window.onload = function(){
  insertList();
  document.getElementById("getJoke").addEventListener("submit",getJokeByID);
  document.getElementById("randomButton").addEventListener("click",getRandomJoke);
  
};