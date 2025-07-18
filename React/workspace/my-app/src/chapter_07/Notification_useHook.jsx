import { useEffect } from "react";

const styles = {
    wrapper:{
        margin:8,
        padding:8,
        flexDirection:"row",
        border:"1px solid grey",
        borderRadius:16,
    },
    messageText:{
        color:"black",
        fontSize:16,
    },
};
function Notification_useHook(props){
    useEffect(()=>{
        console.log(props.id+"componentDidMount() 실행.");
        return ()=>{
            console.log(props.id+"componentWillUnmount() 실행.");
        }
    },[])
    useEffect(()=>{
        console.log(props.id+"componentDidUpdate() 실행.")
    })
    return (
        <div style={styles.wrapper}>
            <span style={styles.messageText}>
                {props.message}
            </span>
        </div>
    )
}
export default Notification_useHook;