
let header = "<tr><th>id</th><th>type</th></tr>";

let insertList = function (event) {
    let result = fetch("api/jokes/all")
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                let prepData = data.map(formatToTR);
                document.getElementById("jokeTable").innerHTML = header + prepData.join("");
            });
};

let formatToTR = function (item) {
    return "<tr><td>" + item.id + "</td><td>" + item.type + "</td></tr>";
};

let getJokeByID = function (event) {
    event.preventDefault();
    let id = document.getElementById("input").value;

    let result = fetch("api/jokes/id/" + id)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                if (data.type == "meme image") {
                    document.getElementById("jokeOut").innerHTML = "<img src=\"media/images/meme/" + data.story + ".jpg\">"
                } else {
                    document.getElementById("jokeOut").innerHTML = "<p>" + data.story + "</p>";
                }

            });
};

let getRandomJoke = function () {
    let result = fetch("api/jokes/random")
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                if (data.type == "meme image") {
                    document.getElementById("jokeOut").innerHTML = "<img src=\"media/images/meme/" + data.story + ".jpg\">"
                } else {
                    document.getElementById("jokeOut").innerHTML = "<p>" + data.story + "</p>";
                }
            });
}

let getRandomVideoJoke = function () {
    let random = Math.floor(Math.random() * 4) + 1;
    let result = fetch("https://gist.githubusercontent.com/Lforlasse/9cb71c25cfd6cdbcec5d712c797e3e6b/raw/b9a353aa3d1e7b2d737fbfa103b45072e3261875/gistfile1.txt")
            .then(res => res.json()) //in flow1, just do it
            .then(data => { console.log(data)
                // Inside this callback, and only here, the response data is available
                data.map(video => {
                    if (video.id === random) {
                        document.getElementById("jokeVideoOut").innerHTML = video.link;
                    }
                });
            });
};

window.onload = function () {
    insertList();
    document.getElementById("getJoke").addEventListener("submit", getJokeByID);
    document.getElementById("randomButton").addEventListener("click", getRandomJoke);

};