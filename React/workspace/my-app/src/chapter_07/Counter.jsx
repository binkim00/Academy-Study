import { useState } from "react";

function Counter(props){
    // var count = 0; 
    //useState
    //1. 화면에 출력하기 위한 데이터를 저장하는 변수처럼 사용되는 훅
    //2. useState로 선언된 값이 변경되면 화면이 리렌더링 된다
    //3. 리렌더링 시 다른데이터는 초기화 되지만 state데이터는 유지됨
    //4. state의 값 변경시 setState 함수를 사용하여 변경해야함
    //5. 함수 선언부 바로 아래에 작성하기
    // const [state명, setState함수명] = useState(초기값);
    const [count, setCount] = useState(0);
    return (
        <div>
            <p>총 {count}번 클릭했습니다.</p>
            <button onClick={()=>setCount(count+1)}>
                클릭
            </button>
        </div>
    )
}
export default Counter;