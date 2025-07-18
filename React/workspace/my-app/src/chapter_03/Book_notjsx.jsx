import React from "react";
// Book 컴포넌트 생성,jsx 사용하지 않고 작성한 Book 컴포넌트 코드
function Book(props) {
        return React.createElement(
        'div',
        null,
        [
            React.createElement(
                'h1',
                null,
                '이 책의 이름은 ${props.name}입니다.'
            ),
        React.createElement(
            'h2',
            null,
            '이 책은 총${props.numOfpage}페이지로 이뤄져 있습니다.'
            )
        ]
    )
}

// Book.jsx 파일을 불렀을 때 기보느로 돌려주는 함수는 Book으로 설정
export default Book;