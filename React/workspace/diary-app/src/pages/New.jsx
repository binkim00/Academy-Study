import { useNavigate } from "react-router-dom";
import Editor from "../component/Editor/Editor";
import Header from "../component/ui/Header";
import Button from "../component/ui/Button";
import { DiaryDispatchContext } from "../App";
import { useContext, useEffect } from "react";
import { setPageTitle } from "../utils";

const New = () =>{
    const navigate = useNavigate();
    const { onCreate } = useContext(DiaryDispatchContext);
    
    useEffect(()=>{
        setPageTitle("새 일기 쓰기");
    },[])

    const onSubmit = (data) =>{
        const {date, emotionId, content} = data;
        onCreate(date,content, emotionId);
        // {replace:true} : 뒤로가기를 못하도록 만드는 옵션
        navigate("/", {replace:true});
    }
    const goBack = () =>{
        navigate(-1);
    }
    return (
        <div>
            <Header title={"새 일기 쓰기"} 
            leftChild={<Button text={"<뒤로가기"} onClick={()=>navigate(-1)}/>} />
            <Editor onSubmit={onSubmit}/>
        </div>
    );
}
export default New;