/**
 * 
 * 
 */


let header = "<tr id=\"header\"><th scope=\"col\">ID</th><th scope=\"col\">Name</th><th scope=\"col\">Favorite TV-show/Game/etc...</th></tr>";

let insertList = function(event){
    let result = fetch("api/member")
   .then(res => res.json()) //in flow1, just do it
   .then(data => {
        // Inside this callback, and only here, the response data is available
        let prepData = data.map(formatToTR);
        document.getElementById("membertable").innerHTML = header + prepData.join("");
    });
};

let formatToTR = function(item){
    return "<tr><td>" + item.id + "</td><td>" + item.name + "</td><td>" + item.fave + "</td></tr>"; 
};


window.onload = function(){
    insertList();
    document.getElementById("reload").addEventListener("click",insertList);
}


