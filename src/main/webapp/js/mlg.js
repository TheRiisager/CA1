window.addEventListener("click",function(){
   document.getElementById("mlg").play();
   
   document.body.style.cursor = "URL(media/images/mlg.png), crosshair";
   setTimeout(() => {document.body.style.cursor = "default";}, 200);
});


