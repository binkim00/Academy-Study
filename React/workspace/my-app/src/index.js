import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Library from './chapter_03/Library';
import Clock from './chapter_04/Clock';
import CommentList from './chapter_05/CommentList';
import NotificationList from './chapter_06/NotificationList';
import Counter from './chapter_07/Counter';
import Counter_useEffect from './chapter_07/Counter_useEffect';
import NotificationList_useHook from './chapter_07/NotificationList_useHook';
import TextInputWithFocusButton from './chapter_07/TextInputWithFocusButton';
import Accommodate from './chapter_07/Accommodate';
import ConfirmButton from './chapter_08/ConfirmButton';
import LandingPage from './chapter_09/LandingPage';
import AttendanceBook from './chapter_10/AttendanceBook';
import GetImage from './chapter_10/GetImage';
import Practice1 from './practice/Practice1';
import NameForm from './chapter_11/NameForm';
import Practice2 from './practice/Practice2';
import Calculator from './chapter_12/Calculator';
import ProfileCard from './chapter_13/ProfileCard';
import DarkOrLight from './chapter_14/DarkOrLight';
import Blocks from './chapter_15/Blocks';

const root = ReactDOM.createRoot(document.getElementById('root'));
// setInterval(()=>{
root.render(
  <React.StrictMode>
    {/* <App /> */}
    {/* <Library /> */}
    {/* <Clock /> */}
    {/* <CommentList /> */}
    {/* <NotificationList /> */}
    {/* <Counter /> */}
    {/* <Counter_useEffect /> */}
    {/* <NotificationList_useHook /> */}
    {/* <TextInputWithFocusButton /> */}
    {/* <Accommodate /> */}
    {/* <ConfirmButton /> */}
    {/* <LandingPage /> */}
    {/* <AttendanceBook /> */}
    {/* <GetImage /> */}
    {/* <Practice1 /> */}
    {/* <NameForm /> */}
    {/* <Practice2 /> */}
    {/* <Calculator /> */}
    {/* <ProfileCard /> */}
    {/* <DarkOrLight /> */}
    <Blocks />
  </React.StrictMode>
)
//}, 1000)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
