import { useEffect, useState } from "react";

function Practice2(props){
    const [user, setUser] = useState({id:"",pw:"", pwc:""});
    const [message, setMessage] = useState("");
    const changeUser = (event) =>{
        // user객체에 변경된 데이터를 설정
        // setState는 기존 객체를 변경하는것은 리렌더링되지 않음
        // 새로운 객체를 만들어서 설정
        // prev : 기존 데이터
                      // { id:"",pw:"", pwc:"", id:value }
        setUser((prev)=>({...prev, [event.target.name]:event.target.value}))
    }
    useEffect(()=>{
        if(user.id==="" || user.pw==="" || user.pwc === ""){
            setMessage("빈 칸을 모두 입력하세요")
        }else if(user.pw !== user.pwc){
            setMessage("비밀번호가 일치하지 않습니다.");
        }else if(user.id==="tester"){
            setMessage("이미 존재하는 계정입니다.");
        }else{
            setMessage("");
        }
    },[user])
    const handleSubmit = (e) =>{
        e.preventDefault();
        if(message === ""){
            alert(`ID:${user.id} \r\nPassword1:${user.pw} \r\nPassword2:${user.pwc}`);
        }else{
            alert("작성한 내용을 확인해주세요.")
        }
    }
    return(
        <form onSubmit={handleSubmit}>
            <h1>REACTERS</h1>
            <h2>회원가입</h2>
            <p><input type="text" name="id" value={user.id} onChange={changeUser} placeholder="아이디"/></p>
            <p><input type="password" name="pw" value={user.pw} onChange={changeUser} placeholder="비밀번호"/></p>
            <p><input type="password" name="pwc" value={user.pwc} onChange={changeUser} placeholder="비밀번호 확인"/></p>
            <p>{message}</p>
            <button>회원가입</button>
        </form>
    );
}
export default Practice2;