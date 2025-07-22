import React from "react";
import styled from "styled-components";

const StyledButton = styled.button`
    padding: 8px 16px;
    font-size: 16px;
    border-width: 1px;
    border-radius: 8px;
    cursor: pointer;
`;

function Button(props) {
    // title: 버튼의 이름
    // onClick: 버튼이 실행할 함수를 props로 받아옴
    const { title, onClick } = props;

    return (
    <StyledButton onClick={onClick}>
        {title || "button"}
    </StyledButton>
    );
}

export default Button;
