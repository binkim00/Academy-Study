import React from "react";
import Book from "./Book";
// Library 컴포넌트 생성
// 1. 첫글자를 대문자로 작성
// 2. 파일 이름과 똑같이 작성
// 3. 컴포넌트를 사용하려면 import 해야함
function Library(props) {
    return (
        // return 안에는 반드시 하나의 태그를 작성하고 그 안에 다른 태그를 작성
        // <div></div> : 에러 발생
        <div>
            {/* 컴포넌트 사용 */}
            {/* 1. 컴포넌트 이름은 import문에 설정한 이름을 사용 */}
            {/* 2. 컴포넌트에 전달할 매개변수는 컴포넌트 옆에 작성, props.name 형식으로 사용 */}
            {/* 3. 문자열은 ""(큰따움표)로 감싸고 그 외의 데이터는 {}(중괄호)로 감싸서 전달*/}
            {/* 4. JSX는 종료태그가 없는 경우 마지막에 /를 붙여서 작성 */}
            <Book name="처음 만난 파이썬" numOfPage={300} />
            <Book name="처음 만난 AWS" numOfPage={400} />
            <Book name="처음 만난 리액트" numOfPage={500} />
        </div>
    );
}

export default Library;