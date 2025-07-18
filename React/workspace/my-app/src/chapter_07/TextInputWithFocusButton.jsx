import { useRef } from "react";

function TextInputWithFocusButton(){
    // useRef선언, 태그나 값을 저장하는 객체 생성
    // 리렌더링되더라도 값이 초기화되지 않음
    // useRef의 값이 변경되더라도 리렌더링이 발생하지 않음
    const inputElem = useRef(null);
    const count = useRef(0);
    const onButtonClick = () =>{
        inputElem.current.focus();
        // input태그에 있는 value값을 출력
        console.log(inputElem.current.value);
        count.current++;
        console.log(count);
    }
    return (
        <>
            {/* ref : 태그와 useRef를 연결하는 속성 */}
            {/* ref = {useRef로 만든 변수 이름} */}
            <input ref={inputElem} type="text"/>
            <button onClick={onButtonClick}>Focus the input</button>
            <p>{count.current}</p>
        </>
    );
}
export default TextInputWithFocusButton;