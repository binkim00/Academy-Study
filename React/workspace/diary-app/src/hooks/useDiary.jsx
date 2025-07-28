import { useContext, useEffect, useState } from "react";
import { DiaryStateContext } from "../App";
import { useNavigate } from "react-router-dom";
const useDiary = (id) =>{
    // 전체 일기 데이터 저장
    const data = useContext(DiaryStateContext);
    // id에 맞는 일기 데이터를 저장할 state를 생성
    const [diary, setDiary] = useState();
    const navigate = useNavigate();
    useEffect(()=>{
        const matchDiary = 
            // 배열.find((배열데이터)=> 조건식) : 조건식에 맞는 데이터 한개를 반환
            data.find((item)=>String(item.id) === String(id));
        if(matchDiary){
            setDiary(matchDiary);
        }else{
            alert("일기가 존재하지 않습니다.");
            navigate("/", {replace:true});
        }
    },[id,data]);
    return diary;
}
export default useDiary;