let clickCount = 0;

let audioSources = ["media/audio/noone.mp3", "media/audio/where.mp3"]


window.addEventListener("click",function(){
   document.getElementById("mlg").play();

   if(clickCount == 10){
      document.getElementById("audiosrc").src = "media/audio/noone.mp3";
      console.log(document.getElementById("audiosrc").currentSrc);
      document.getElementById("reacts").load();
      document.getElementById("reacts").play();
   }

   if(clickCount == 20){
      document.getElementById("audiosrc").src = "media/audio/where.mp3";
      console.log(document.getElementById("audiosrc").currentSrc);
      document.getElementById("reacts").load();
      document.getElementById("reacts").play();
   }
   
   document.body.style.cursor = "URL(media/images/mlg.png), crosshair";
   setTimeout(() => {document.body.style.cursor = "default";}, 200);
   clickCount++;
   console.log(clickCount);
});


