

let playMeme = function (){
    document.getElementById("memesrc").play();
}

let pauseMeme = function (){
    document.getElementById("memesrc").pause();
}

window.onload = function () {
    document.getElementById("memetarget").addEventListener("mouseenter",playMeme)
    document.getElementById("memetarget").addEventListener("mouseleave",pauseMeme)
}

