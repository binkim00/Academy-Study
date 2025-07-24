import "./DiaryList.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import DiaryItem from "./DiaryItem";
import Button from "../ui/Button";

const sortOptionList = [
    {value:"latest", name:"최신순", },
    {value:"oldest", name:"오래된순", },
];

const DiaryList = ({data}) =>{
    const navigate = useNavigate();
    const [sortType, setSortType] = useState("latest");
    const [sortedData, setSortedData] = useState([]);
    useEffect(()=>{
        // 정렬에 사용할 비교 방식 설정
        const compare = (a,b) =>{
            if(sortType === "latest"){
                return Number(b.date) - Number(a.date);
            }else{
                return Number(a.date) - Number(b.date);
            }
        }
        // 메모리 주소가 아닌 데이터를 복사하기 위해(깊은 복사)
        const copyList = JSON.parse(JSON.stringify(data));
        // sortType에 따라 정렬처리를 실행
        copyList.sort(compare);
        // state에 데이터 저장
        setSortedData(copyList);
    },[data, sortType]);
    const onClickNew = () =>{
        navigate("/new");
    }
    const onChangeSortType = (e)=>{
        setSortType(e.target.value);
    }
    return (
        <div className="DiaryList">
            <div className="menu_wrapper">
                <div className="left_col">
                    <select value={sortType} onChange={onChangeSortType}>
                        {sortOptionList.map((item, idx)=>(
                            <option key={idx} value={item.value}>
                                {item.name}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="right_col">
                    <Button type={"positive"} text={"새 일기 쓰기"} onClick={onClickNew} />
                </div>
            </div>
            <div className="list_wrapper">
                {sortedData.map((item)=>(
                    <DiaryItem key={item.id} {...item} />
                ))}
            </div>
        </div>
    );
}
export default DiaryList;