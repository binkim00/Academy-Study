import './App.css';
import {BrowserRouter, Link, Route, Routes} from 'react-router-dom';
import Home from './pages/Home';
import New from './pages/New';
import Diary from './pages/Diary';
import Edit from './pages/Edit';
import React, { useEffect, useReducer, useRef, useState } from 'react';
const mockData = [
  {id:"1", date:new Date("2025-06-01").getTime(), content:"mock content1", emotionId:1,},
  {id:"2", date:new Date("2025-07-01").getTime(), content:"mock content2", emotionId:2,},
  {id:"3", date:new Date("2025-08-01").getTime(), content:"mock content3", emotionId:3,},
];

function reducer(state, action){
  switch(action.type){
    case "INIT" : {return action.data}
    case "CREATE" : {
      const newState = [action.data, ...state];
      localStorage.setItem("diary", JSON.stringify(newState));
      return newState;
    }
    case "UPDATE" : {
      // 기존에 있던 일기 데이터를 변경
      const newState = state.map((data)=>
        //   기존id와 수정하는 id가 같은지 확인 ? 변경된일기를 저장 : 기존의일기 저장
        String(data.id) === String(action.data.id) ? {...action.data} : data);
      localStorage.setItem("diary", JSON.stringify(newState));
      return newState;
    }
    case "DELETE" : {
      //filter((data) => 조건식이 true일 경우 저장하는 함수)
      const newState = state.filter((data)=>
        // 기존id 와 삭제할 id가 같다면 제외
       // 다르다면 다시 저장
        String(data.id) !== String(action.targetId)
      );
      localStorage.setItem("diary", JSON.stringify(newState));
      return newState;
    }
  }
}
// Data를 전달하는 Context
export const DiaryStateContext = React.createContext();
// Data를 변경하는 함수를 전달하는 Context
export const DiaryDispatchContext = React.createContext();
function App() {
  // useReducer(변경함수, 초기값) : setState함수가 너무 복잡할 경우 컴포넌트
  // 외부에 setState함수를 작성할 수 있도록 하는 훅
  const [data, dispatch] = useReducer(reducer, []);
  const idRef = useRef(0); // id데이터가 1씩 더해질 수 있도록 변수에 저장
  // 데이터가 로딩중인지 아닌지 확인하는 state
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  const onCreate = (date,content,emotionId)=>{
    dispatch({
      type:"CREATE",
      data:{
        id : idRef.current,
        date: new Date(date).getTime(),
        content,
        emotionId,
      }
    });
    idRef.current += 1; 
  }
  const onUpdate = (id,date,content,emotionId)=>{
    dispatch({
      type:"UPDATE",
      data:{
        id : id,
        date: new Date(date).getTime(),
        content,
        emotionId,
      }
    });
  }
  const onDelete = (targetId)=>{
    dispatch({
      type:"DELETE",
      targetId
    });
  }
  useEffect(()=>{
    // 임시 데이터를 localStorage에 저장 개발 완료 후 삭제
    // localStorage.setItem("diary", JSON.stringify(mockData));
    const rawData = localStorage.getItem("diary");
    if(!rawData){
      setIsDataLoaded(true);
      return;
    }
    // localStorage에 데이터가 있으면 자바스크립트 데이터로 변환
    const localData = JSON.parse(rawData);
    // diary데이터가 빈 배열인 경우 실행
    if(localData.length === 0){
      setIsDataLoaded(true);
      return;
    }
    // id값을 기준으로 내림차순으로 정렬
    localData.sort((a,b)=>Number(b.id) - Number(a.id));
    // 새로운 일기를 저장할때의 id를 설정
    idRef.current = localData[0].id + 1;
    // 초기 데이터 저장
    dispatch({
      type:"INIT",
      data:localData,
    })
    setIsDataLoaded(true);
  },[])
  if(!isDataLoaded){
    return(<div>데이터를 불러오는 중입니다.</div>);
  }else{
    return (
      <BrowserRouter>
      <DiaryStateContext.Provider value={data}>
      <DiaryDispatchContext.Provider value={{onCreate,onUpdate,onDelete}}>
      <div className="App">
        <Routes>
          <Route index element={<Home />} />
          <Route path="new" element={<New />} />
          <Route path="diary/:id" element={<Diary />} />
          <Route path="edit/:id" element={<Edit />} />
        </Routes>
        <div>
          {/* Link : 리액트에서 페이지 이동시 사용하는 컴포넌트 */}
          {/* 새로고침을 하지 않고 Route에 설정된 경로로 이동시키는 기능 */}
          {/* <Link to={"/"}>Home</Link>
          <Link to={"/new"}>New</Link>
          <Link to={"/diary/1"}>Diary</Link>
          <Link to={"/edit/1"}>Edit</Link> */}
        </div>
      </div>
      </DiaryDispatchContext.Provider>
      </DiaryStateContext.Provider>
      </BrowserRouter>
    );
  }
}

export default App;
