import { emotionList } from "../../utils";
import "./Viewer.css";

const Viewer = ({emotionId, content}) =>{
    // emotionId를 이용하여 감정 데이터 저장
    const emotionItem = emotionList.find((item)=>item.id === emotionId);
    return (
        <div className="Viewer">
            <section>
                <h4>오늘의 감정</h4>
                <div className={
                    ["emotion_img_wrapper", `emotion_img_wrapper_${emotionId}`].join(" ")}
                >
                    <img src={emotionItem.img} alt={emotionItem.name}/>
                    <div className="emotion_descript">{emotionItem.name}</div>
                </div>
            </section>
            <section>
                <h4>오늘의 일기</h4>
                <div className="content_wrapper">
                    <p>{content}</p>
                </div>
            </section>
        </div>
    );
}
export default Viewer;