import "./Editor.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { emotionList, getFormattedDate } from "../../utils";
import EmotionItem from "./EmotionItem";
import Button from "../ui/Button";
//            초기화데이터, 작성완료버튼함수 
const Editor = ({initData, onSubmit}) =>{
    const navigate = useNavigate();
    const [state, setState] = useState(
        {
            date: getFormattedDate(new Date()),
            emotionId:3,
            content:"",
        }
    );
    useEffect(()=>{
        
        // 수정페이지의 경우 초기화데이터를 설정
        if(initData){
            setState({
                ...initData,
                date:getFormattedDate(new Date(parseInt(initData.date))),
            });
        }
    },[initData]);
    
    const handleGoBack = () =>{
        // -1을 설정하면 뒤로가기가 실행됨
        navigate(-1);
    }
    const handleChangeDate = (event) =>{
        setState({
            ...state,
            date : event.target.value,
        })
    }
    const handleChangeContent = (event) =>{
        setState({
            ...state,
            content : event.target.value,
        })
    }
    const handleChangeEmotion = (emotionId) =>{
        setState({
            ...state,
            emotionId,
        })
    }
    const handleSubmit = () =>{
        onSubmit(state);
    }
    return(
        <div className="Editor">
            <div className="editor_section">
                <h4>오늘의 날짜</h4>
                <div className="input_wrapper">
                    <input 
                        type="date" 
                        value={state.date} 
                        onChange={handleChangeDate}/>
                </div>
            </div>
            <div className="editor_section">
                <h4>오늘의 감정</h4>
                <div className="input_wrapper emotion_list_wrapper">
                    {emotionList.map((emotion)=>(
                        <EmotionItem
                            key={emotion.id}
                            {...emotion}
                            onClick={handleChangeEmotion}
                            isSelected={state.emotionId === emotion.id}
                        />
                    ))}
                </div>
            </div>
            <div className="editor_section">
                <h4>오늘의 일기</h4>
                <div className="input_wrapper">
                    <textarea 
                        placeholder="오늘은 어땠나요?"
                        value={state.content}
                        onChange={handleChangeContent}
                    />
                </div>
            </div>
            <div className="editor_section bottom_section">
                <Button text={"취소하기"} onClick={handleGoBack} />
                <Button text={"작성완료"} onClick={handleSubmit} type={"positive"}/>
            </div>
        </div>
    );
}
export default Editor;