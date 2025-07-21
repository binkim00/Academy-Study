import { useContext } from "react";
// ThemeContext를 사용하려면 반드시 임포트를 해야함
import ThemeContext from "./ThemeContext";
// export default 가 아닌 
// export의 경우 중괄호안에 선언시 사용한 이름을 작성하여 import 해야함
import {ThemeContext2} from "./DarkOrLight";

function MainContent(props){
    // ThemeContext에 있는 value를 꺼내려면 useContext 사용해야함 
    const {theme, toggleTheme} = useContext(ThemeContext);
    const value2 = useContext(ThemeContext2);
    return (
        <div style={{
            width:"100vw",
            height:"100vh",
            padding:"1.5rem",
            // 테마의 CSS를 변경하는 부분
            backgroundColor:theme=="light" ? "white" : "black",
            color:theme == "light"?"black":"white",
        }}
        >
            <p>안녕하세요. 테마 변경이 가능한 웹사이트 입니다.{value2}</p>
            {/* 테마를 변경하는 부분 */}
            <button onClick={toggleTheme}>테마 변경</button>
        </div>
    );
}
export default MainContent;