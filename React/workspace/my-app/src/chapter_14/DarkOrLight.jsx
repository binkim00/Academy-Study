import React, { useCallback, useState } from "react";
// context를 임포트하는 부분
import ThemeContext from "./ThemeContext";
import MainContent from "./MainContent";

//DarkOrLight위에 바로 코드를 작성하는 것도 가능
export const ThemeContext2 = React.createContext();
ThemeContext2.displayName = "ThemeContext2";

function DarkOrLight(props){
    // context에 저장할 데이터 설정, 의도치 않은 리렌더링 방지를 위해 state를 사용
    const [theme, setTheme] = useState("light");
    // 최적화 함수인 useCallback을 이용하여 toggleTheme 함수를 작성
    // 테마의 경우 자주 변경하지 않기 때문에 최적화 함수 사용
    const toggleTheme = useCallback(()=>{
        if(theme == "light"){
            setTheme("dark");
        }else if(theme == "dark"){
            setTheme("light");
        }
    },[theme]);
    return (
        // 여러개의 컨텍스트를 사용할 경우 컨텍스트 안에 컨텍스트를 적는 방식으로 작성
        <ThemeContext2.Provider value={"123"}>
        {/* Context에 데이터를 설정하는 Provider */}
            <ThemeContext.Provider value={{theme, toggleTheme}}>
                {/* Provider안에 있는 컴포넌트는 모두 value를 사용할 수 있음 */}
                {/* Provider밖에 있는 컴포넌트는 value를 사용할 수 없음 */}
                <MainContent />
            </ThemeContext.Provider>
        </ThemeContext2.Provider>
    );
}
export default DarkOrLight;