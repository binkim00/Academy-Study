import { useEffect, useState } from "react";
import useCounter from "./useCounter";

// 입장가능한 인원의 최대값을 10명으로 설정
const MAX_CAPACITY = 10;

function Accommodate(props){
    // 정원초과 문구를 출력하는 값, 화면갱신이 필요하기 때문에 state로 저장
    const [isFull, setIsFull] = useState(false);
    // 커스텀 훅을 이용하여 필요한 변수 및 함수 저장
    const [count, increaseCount, decreaseCount] = useCounter(0);
    useEffect(()=>{
        console.log("======================");
        console.log("useEffect() is called.");
        console.log(`isFull: ${isFull}`);
    },[isFull, count])
    // 카운트 값이 변결될때마다 실행되는 useEffect
    useEffect(()=>{
        // count가 10되면 isFull을 true로 변경
        // count 10보다 작으면 isFull을 false변경
        setIsFull(count >= MAX_CAPACITY)
        console.log(`Current count value: ${count}`);
    },[count]);

    return (
        // style을 직접 설정시 {{}}로 감싸서 사용, 자바스크립트 객체 형식으로 사용
        <div style={{padding:16}}>
            <p>{`총 ${count}명 수용했습니다.`}</p>
            {/* jsx의 경우 on 다음의 글자를 대문자로 작성 */}
            <button onClick={increaseCount} disabled={isFull}>
                입장
            </button>
            <button onClick={decreaseCount}>퇴장</button>
            {/* 조건식 && 실행문 */}
            {isFull && <p style={{color:"red"}}>정원이 가득찼습니다.</p>}
        </div>
    );
}
export default Accommodate;