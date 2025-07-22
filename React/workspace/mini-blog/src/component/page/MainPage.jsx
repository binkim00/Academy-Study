import styled from "styled-components";
import { useNavigate } from "react-router-dom";
// 로컬 스토리지에서 받아오도록 변경
// import data from '../../data.json';
import Button from "../ui/Button";
import PostList from "../list/PostList";

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
function MainPage(props){
    const {} = props;
    // a태그 대신 사용하는 useNavigate()
    // 페이지 이동시 새로고침이 일어나지 않음
    const navigate = useNavigate();

    // 로컬스토리지 저장시 사용한 key를 이용하여 데이터를 불러옴
    const jsonData = localStorage.getItem("blog");
    // Json데이터를 자바스크립트 데이터로 변경
    const data = JSON.parse(jsonData);
    return (
        <Wrapper>
            <Container>
                <Button 
                    title="글 작성하기" 
                    onClick={()=>{
                        // /post-write주소로 이동
                        navigate("/post-write");
                    }}
                />
                <PostList 
                    posts={data} 
                    onClickItem={(item)=>{
                        // /post/id 주소로 이동
                        navigate(`/post/${item.id}`);
                    }}
                />
            </Container>
        </Wrapper>
    );
}
export default MainPage;