import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import Button from "../ui/Button";
import TextInput from "../ui/TextInput";

const Wrapper = styled.div`
    padding: 16px;  
    width:calc(100% - 32px);
    display:flex;
    flex-direction:column;
    align-items:center;
    justify-content:center;
`;
const Container = styled.div`
    width:100%;
    max-width:720px;

    :not(:last-child){
        margin-bottom:16px;
    }
`;
function PostWritePage(props){
    const navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const handleWrite = () =>{
        // 원본데이터를 변수에 저장
        const data = JSON.parse(localStorage.getItem("blog"));
        // 최신 id를 저장
        const lastId = Math.max(...data.map(item => item.id));
        // 저장할 게시글 객체 생성
        const post = {
            id:lastId+1,
            title:title,
            content:content,
            comments:[],
        }
        //원본 객체에 post를 추가하여 새로운 객체를 생성
        const saveData = [post , ...data];
        // 객체를 json데이터로 변환
        const jsonSaveData = JSON.stringify(saveData);
        // localStorage에 저장
        localStorage.setItem("blog", jsonSaveData);
        // MainPage로 이동
        navigate("/");
    }
    return(
        <Wrapper>
            <Container>
                <TextInput
                    height={20}
                    value={title}
                    onChange={(event)=>{
                        setTitle(event.target.value);
                    }}
                />
                <TextInput
                    height={480}
                    value={content}
                    onChange={(event)=>{
                        setContent(event.target.value);
                    }}
                />
                <Button
                    title="글 작성하기"
                    onClick={handleWrite}
                />
            </Container>
        </Wrapper>
    );
}
export default PostWritePage;