// HTML 문서가 완전히 로드되면 실행될 함수 등록
document.addEventListener("DOMContentLoaded", function () {

  // 큰 이미지를 보여주는 img 태그를 가져온다 (id="mainImage")
  const mainImage = document.getElementById("mainImage");

  // 작은 이미지(썸네일 역할을 하는 div들)를 모두 선택해서 가져온다
  const thumbs = document.querySelectorAll(".sImg div");

  // 각각의 썸네일에 클릭 이벤트를 추가한다
  thumbs.forEach(thumb => {
      thumb.addEventListener("click", () => {

          // 클릭한 썸네일에 있는 data-src 속성값(이미지 경로)을 가져온다
          const imgPath = thumb.getAttribute("data-src");

          // 큰 이미지 영역의 src 속성을 클릭한 이미지로 바꾼다
          mainImage.setAttribute("src", imgPath);

          // 모든 썸네일의 테두리를 지운다
          thumbs.forEach(t => t.style.border = "none");

          // 현재 클릭한 썸네일만 테두리를 빨간색으로 설정한다
          thumb.style.border = "5px solid red";
      });
  });
});

/* testD */
window.onload = function () {
   // 탭 내용 박스들 (HTML, CSS, JS, JQuery 내용 div)
  const tabContents = document.querySelectorAll("#tabContainer div");
    // 탭 버튼들 (ul.tabBtn 안에 있는 li들)
  const tabButtons = document.querySelectorAll(".tabBtn li");
  // 각 버튼에 클릭 이벤트 추가
  for (let i = 0; i < tabButtons.length; i++) {
      // 현재 버튼의 순서를 기억
      tabButtons[i].onclick = function () {
        // 모든 버튼에서 'on' 클래스 제거
          for (let j = 0; j < tabButtons.length; j++) {
              tabButtons[j].classList.remove("on");
              tabContents[j].classList.remove("on");
          }
          // 클릭한 버튼에만 'on' 클래스 추가
          tabButtons[i].classList.add("on");
          tabContents[i].classList.add("on");
      }
  }
};
