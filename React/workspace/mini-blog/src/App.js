import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import MainPage from './component/page/MainPage';
import PostWritePage from './component/page/PostWritePage';
import styled from 'styled-components';
import PostViewPage from './component/page/PostViewPage';

const MainTitleText = styled.p`
  font-size:24px;
  font-weight:bold;
  text-align:center;
`;

function App() {
  return (
    <BrowserRouter>
      <MainTitleText>소플의 미니 블로그</MainTitleText>
      <Routes>
        {/* 주소 : http://localhost:3000 */}
        <Route index element={<MainPage />} />
        {/* 주소 : http://localhost:3000/post-write */}
        <Route path="post-write" element={<PostWritePage />} />
        {/* 주소 : http://localhost:3000/post/1 */}
        <Route path="post/:postId" element={<PostViewPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
