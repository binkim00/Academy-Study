import { useContext, useEffect, useState } from "react";
import Button from "../component/ui/Button";
import Header from "../component/ui/Header";
import DiaryList from "../component/List/DiaryList";
import { DiaryStateContext } from "../App";
import {getMonthRangeByDate, setPageTitle} from "../utils";
const Home = () =>{
    const data = useContext(DiaryStateContext);
    const [pivotDate, setPivotDate] = useState(new Date());
    // 자바스크립트 getMonth()는 0~11월까지로 계산하기 때문에 +1 이 필요함
    const headerTitle = `${pivotDate.getFullYear()}년 ${pivotDate.getMonth()+1}월`;
    const [filteredData, setFilteredData] = useState([]);
    useEffect(()=>{
        if(data.length >= 1){
            const {beginTimeStamp, endTimeStamp} = getMonthRangeByDate(pivotDate);
            setFilteredData(
                data.filter((item)=> item.date >= beginTimeStamp 
                                    && item.date <= endTimeStamp)
            );
        }else{
            setFilteredData([]);
        }
        // 로딩이 끝나면 타이틀을 변경
        setPageTitle("Winterlood의 감성 일기장");
    },[data, pivotDate]);

    const onIncreaseMonth = () =>{
        setPivotDate(new Date(pivotDate.getFullYear(), pivotDate.getMonth()+1));
    }
    const onDecreaseMonth = () =>{
        setPivotDate(new Date(pivotDate.getFullYear(), pivotDate.getMonth()-1));
    }
    return (
        <div>
            <Header
            title={headerTitle}
            leftChild={<Button text="<" onClick={onDecreaseMonth}/>}
            rightChild={<Button text=">" onClick={onIncreaseMonth}/>}
            />
            <DiaryList data={filteredData} />
        </div>
    );
}
export default Home;