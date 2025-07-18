import { useEffect, useState } from "react";
import Notification_useHook from "./Notification_useHook"

const reservedNotifications = [
    {id:1, message:"안녕하세요, 오늘 일정을 알려드립니다."},
    {id:2, message:"점심 식사 시간입니다."},
    {id:3, message:"이제 곧 미팅이 시작됩니다."},
]
var timer;
function NotificationList_useHook(props){
    const [notifications, setNotications] = useState([]);
    useEffect(()=>{
        const noti = notifications;
        timer = setInterval(()=>{
            if(noti.length < reservedNotifications.length){
                const index = noti.length;
                noti.push(reservedNotifications[index]);
                // ... : 스프레드 연산자 , 배열 데이터를 하나하나 나누어주는 연산자
                // [1,2,3] => 1,2,3
                //             매개변수 => 실행할 내용 
                setNotications((prev)=>{
                   return [...noti];
                });
            }else{
                clearInterval(timer);
                setNotications([]);
            }
        },1000);
        return ()=>{
            if(timer){
                clearInterval(timer);
            }
        }
    },[])
    return(
        <div>
            {notifications.map((notification)=>{
                return <Notification_useHook key={notification.id} id={notification.id} message={notification.message}/>
            })}
        </div>
        )
}
export default NotificationList_useHook;