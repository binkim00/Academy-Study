import { useEffect, useState } from "react";

function Counter_useEffect(){
    const [page, setPage] = useState(0);
    const [count, setCount] = useState(0);
    //           실행함수   , 의존성배열
    // useEffect(()=>{}    ,     []    )
    // componentDidMount와 같이 컴포넌트 생성시 실행됨
    useEffect(()=>{
        console.log("[], useEffect실행 ");
        // componentWillUnmount와 같이 컴포넌트 삭제시 실행됨
        return ()=>{
            console.log("return , useEffect실행 ");
        }
    },[])
    // componentDidUpdate같이 변경되는 값이 있을 경우 실행됨
    // 차이점 : 컴포넌트 생성시에도 실행됨
    useEffect(()=>{
        console.log("공백, useEffect실행")
    })
    // state값이 변경될때 실행
    // 배열 안에있는 값이 변경될때 마다 실행
    // , 배열안에 없는 state가 변경되면 실행되지않음
    useEffect(()=>{
        console.log("[count], useEffect실행")
    },[count])
    
    useEffect(()=>{
        
    },[])
        return (
            <div>
                <p>총 {count}번 클릭했습니다.</p>
                <button onClick={()=>setCount(count+1)}>
                    클릭
                </button>
                <p>{page}페이지</p>
                <button onClick={()=>setPage(page+1)}>
                    클릭
                </button>
                
            </div>
        )
}
export default Counter_useEffect;