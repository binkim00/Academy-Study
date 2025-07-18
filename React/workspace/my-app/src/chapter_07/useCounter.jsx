import { useState } from "react";

function useCounter(initialValue){
    // 인원수를 확인할 숫자를 state선언
    const [count, setCount] = useState(initialValue);
    // count를 1씩 더하는 함수 생성
    const increaseCount = () => setCount((count)=> count + 1);
    // count 1씩 빼는 함수 생성
    // Math.max(숫자1,숫자2) : 숫자1과 숫자2를 비교해서 큰숫자를 반환
    const decreaseCount = () => setCount(() =>Math.max(count-1,0));
    // 완성된 state와 setState두가지를 반환
    return [count, increaseCount, decreaseCount];
}
export default useCounter;