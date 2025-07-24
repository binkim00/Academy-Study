import "./EmotionItem.css"

const EmotionItem = ({id,img,name,onClick,isSelected}) => {
    return(
        <div className={["EmotionItem"
            , isSelected ?`Emotion_on_${id}`:`Emotion_off`].join(" ")}
            onClick={()=>onClick(id)}>
            <img alt={name} src={img} />
            <span>{name}</span>
        </div>
    );
}
export default EmotionItem;