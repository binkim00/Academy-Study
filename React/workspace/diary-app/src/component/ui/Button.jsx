import "./Button.css"

const Button = ({text,type,onClick}) =>{
    const btnType=["positive", "negative"].includes(type) ? type:"default";
    return(
        <button 
        //  "Button Button_positive"
        //  "Button Button_negative"
        //  "Button Button_default"
        //  [1,2,3,4,5].join(" ") = "1 2 3 4 5"
            className={["Button", `Button_${btnType}`].join(" ") }
            onClick={onClick}>
            {text}
        </button>
    );
}
export default Button;