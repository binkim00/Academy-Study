import { useNavigate } from "react-router-dom";
import Editor from "../component/Editor/Editor";
import Header from "../component/ui/Header";
import Button from "../component/ui/Button";

const New = () =>{
    const navigate = useNavigate();
    const goBack = () =>{
        navigate(-1);
    }
    return (
        <div>
            <Header title={"새 일기 쓰기"} 
            leftChild={<Button text={"<뒤로가기"} onClick={()=>navigate(-1)}/>} />
            <Editor onSubmit={()=>alert("작성 완료했습니다.")}/>
        </div>
    );
}
export default New;