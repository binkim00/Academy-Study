/* testB */
document.addEventListener("DOMContentLoaded", function () {
    const mainImage = document.getElementById("mainImage");
    const thumbs = document.querySelectorAll(".sImg div");
    thumbs.forEach(thumb => {
        thumb.addEventListener("click", () => {
            const imgPath = thumb.getAttribute("data-src");
            mainImage.setAttribute("src", imgPath);
            thumbs.forEach(t => t.style.border = "none");
            thumb.style.border = "5px solid red";
        });
    });
});

/* testD */
window.onload = function () {
    const tabContents = document.querySelectorAll("#tabContainer div");
    const tabButtons = document.querySelectorAll(".tabBtn li");
    for (let i = 0; i < tabButtons.length; i++) {
        tabButtons[i].onclick = function () {
            for (let j = 0; j < tabButtons.length; j++) {
                tabButtons[j].classList.remove("on");
                tabContents[j].classList.remove("on");
            }
            tabButtons[i].classList.add("on");
            tabContents[i].classList.add("on");
        }
    }
};

