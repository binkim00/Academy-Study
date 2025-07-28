import { useNavigate, useParams } from "react-router-dom";
import Header from "../component/ui/Header";
import {DiaryDispatchContext} from "../App";
import { useContext, useEffect } from "react";
import Button from "../component/ui/Button";
import useDiary from "../hooks/useDiary";
import Editor from "../component/Editor/Editor";
import { setPageTitle } from "../utils";

const Edit = () =>{
    const {id} = useParams();
    const diary = useDiary(id);
    const navigate = useNavigate();
    const { onUpdate, onDelete } = useContext(DiaryDispatchContext);

    useEffect(()=>{
        setPageTitle(`${id}번 일기 수정하기`);
    },[])

    const onClickDelete = () =>{
        if(window.confirm("일기를 정말 삭제할까요? 다시 복구되지 않아요!!")){
            onDelete(id);
            navigate("/", {replace:true});
        }
    }
    const onSubmit = (data) =>{
        if(window.confirm("일기를 정말 수정할까요?")){
            const {date, content, emotionId} = data;
            onUpdate(id, date, content, emotionId);
            navigate(`/diary/${id}`,{replace:true});
        }
    }
    return (
        <div>
            <Header title={"일기 수정하기"}
            leftChild={<Button text={"<뒤로가기"} onClick={()=>navigate(-1)} />}
            rightChild={<Button text={"삭제하기"} onClick={onClickDelete} type={"negative"} />}
            />
            <Editor 
                initData={diary} 
                onSubmit={onSubmit}
            />
        </div>
    );
}
export default Edit;