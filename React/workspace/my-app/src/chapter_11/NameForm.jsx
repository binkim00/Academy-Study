import { useRef, useState } from "react";

function NameForm(props){
    // input태그의 내용을 확인하는 코드가 필요한 경우 
    const [value, setValue] = useState("");
    const [selectData, setSelectData] = useState("");
    // 특별한 동작이 필요 없는 경우 
    const nickRef = useRef("");
    // state를 input 태그에 설정했을 경우 
    // 내용을 변경하려면 setState를 사용해야함
    const handleChange = (event) => {
        setValue(event.target.value);
    }
    const handleSelect = (event) =>{
        setSelectData(event.target.value);
    }
    const handleSubmit = (event) =>{
        alert("입력한 이름: "+ value + "\r\n" 
            + "닉네임:"+nickRef.current.value + "\r\n"
            + "자기소개:"+value + "\r\n"
            + "과일:"+selectData)
        event.preventDefault();
    }
    return (
        <form onSubmit={handleSubmit}>
            <label>
                이름 : 
                <input type="text" value={value} onChange={handleChange}/>
            </label><br/>
            <label>
                닉네임 : 
                <input type="text" ref={nickRef}/>
            </label><br/>
            <label>
                자기소개 : 
                {/* JSX에서의 textarea : 종료태그 없음, value속성 사용 가능*/}
                <textarea value={value} onChange={handleChange}/>
                {/* <textarea onChange={handleChange}>
                    {value}
                </textarea> */}
            </label><br/>
            <label>
                과일을 선택하세요:
                <select value={selectData} onChange={handleSelect}>
                    <option value="apple">사과</option>
                    <option value="banana">바나나</option>
                    <option value="grape">포도</option>
                    <option value="watermelon">수박</option>
                </select>
            </label>
            <button type="submit">제출</button>
        </form>
    );
}
export default NameForm;
