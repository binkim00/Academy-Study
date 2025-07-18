import React from "react";
// Book 컴포넌트 생성
function Book(props) {
    // 화면에 출력할 내용들을 적는 JSX 작성 공간
    return (
        <div>
            <h1>{`이 책의 이름은 ${props.name}입니다.`}</h1>
            <h2>{`이 책은 총 ${props.numOfPage}페이지로 이뤄져 있습니다.`}</h2>
        </div>
    );
}
// Book.jsx 파일을 불렀을 때 기보느로 돌려주는 함수는 Book으로 설정
export default Book;