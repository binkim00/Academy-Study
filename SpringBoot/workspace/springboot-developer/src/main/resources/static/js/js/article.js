// id가 delete-btn인 태그를 찾아서 변수에 저장
const deleteButton = document.getElementById('delete-btn');
// id가 delete-btn인 태그가 없는 경우 에러막기위한 if문
if(deleteButton){
    // 삭제 버튼에 클릭 이벤트를 추가
    deleteButton.addEventListener("click", event=>{
        // id가 article-id인 태그의 value값을 변수에 저장
        let id = document.getElementById('article-id').value;
        // 자바스크립트만 사용한 Ajax 통신
        //fetch(실행할 주소, {설정값:메서드, 헤더, 데이터})
        fetch(`/api/articles/${id}`,{
            method:'DELETE'
        })
        // then(()->{fetch가 실행된 후 결과를 받아 실행할 코드})
        .then(()=>{
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        })
    })
}
// 수정버튼을 변수에 저장
const modifyButton = document.getElementById('modify-btn');
if(modifyButton){
    // 수정버튼에 클릭 이벤트를 추가
    modifyButton.addEventListener("click", event=>{
        // 현재 페이지의 주소에서 파라미터만 꺼네어 변수에 저장
        let params = new URLSearchParams(location.search);
        let id = params.get('id');
        fetch(`/api/articles/${id}`,{
            method:'PUT', // GET, POST, PUT, DELETE
            // JSON형식의 데이터를 서버에 전송하는 설정
            headers:{"content-Type":"application/json"},
            // JSON.stringify(자바스크립트 객체) : 자바스크립트 객체를 JSON문자열로 변경
            body : JSON.stringify({
                title:document.getElementById("title").value,
                content:document.getElementById("content").value
            })
        })
        .then(()=>{
            alert('수정이 완료되었습니다.');
            location.replace(`/articles/${id}`);
        })
    })
}

const createButton = document.getElementById('create-btn');
if(createButton){
    // 등록버튼에 클릭 이벤트를 추가
    createButton.addEventListener("click", event=>{
        fetch(`/api/articles`,{
            method:'POST', // GET, POST, PUT, DELETE
            // JSON형식의 데이터를 서버에 전송하는 설정
            headers:{"content-Type":"application/json"},
            // JSON.stringify(자바스크립트 객체) : 자바스크립트 객체를 JSON문자열로 변경
            body : JSON.stringify({
                title:document.getElementById("title").value,
                content:document.getElementById("content").value
            })
        })
        .then(()=>{
            alert('등록 완료되었습니다.');
            location.replace(`/articles`);
        })
    })
}












