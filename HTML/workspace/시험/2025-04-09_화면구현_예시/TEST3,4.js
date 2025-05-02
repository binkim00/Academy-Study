const tabContainer = document.querySelectorAll("#tabContainer div");
const tabBtn = document.querySelectorAll(".tabBtn li");
for(let i=0; i<tabContainer.length; i++){
    tabBtn[i].addEventListener("click",function(){
        for(let j=0; j<tabContainer.length; j++){
            tabContainer[j].classList.remove("on");
            tabBtn[j].classList.remove("on");
        }
        tabContainer[i].classList.add("on");
        tabBtn[i].classList.add("on");
    })
}

const bigImg = document.querySelectorAll(".bigImg img");
const sImg = document.querySelectorAll(".sImg div");
for(let i=0; i<sImg.length; i++){
    sImg[i].addEventListener("click",function(){
        for(let j=0; j<bigImg.length; j++){
            bigImg[j].style.display = "none";
            sImg[j].style.border = "none";
        }
        bigImg[i].style.display="block";
        sImg[i].style.border = "5px solid red";
    })
}