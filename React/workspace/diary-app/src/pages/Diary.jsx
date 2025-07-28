import { useNavigate, useParams } from "react-router-dom";
import Button from "../component/ui/Button";
import Header from "../component/ui/Header";
import useDiary from "../hooks/useDiary";
import { getFormattedDate, setPageTitle } from "../utils";
import Viewer from "../component/Viewer/Viewer";

const Diary = () =>{
    const { id } = useParams();
    const diary = useDiary(id);
    const navigate = useNavigate();

    if(!diary){
        <div>일기를 불러오는 있습니다.</div>
    }else{
        const {date, emotionId, content} = diary; 
        setPageTitle(`${getFormattedDate(new Date(Number(date)))} 기록`);
        const title = `${getFormattedDate(new Date(Number(date)))} 기록`
        return (
            <div>
                <Header title={title}
                leftChild={<Button text={"<뒤로가기"} onClick={()=>navigate(-1)}/>}
                rightChild={<Button text={"수정하기"} onClick={()=>navigate(`/edit/${id}`)}/>}
                />
                <Viewer emotionId={emotionId} content={content}/>
            </div>
        );
    }
}
export default Diary;