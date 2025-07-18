import React from "react";
import './Comment.css';

function Comment(props) {
    return (
        <div className="comment-wrapper">
            <div>
                <img
                    src="https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png"
                    className="comment-image"
                />
            </div>

            <div className="comment-content">
                <span className="comment-name">{props.name}</span>
                <span className="comment-text">{props.comment}</span>
            </div>
        </div>
    );
}

export default Comment;


// // css파일 적용
// import "./comment.css";

// const styles = {
//     //CSS적용의 이름
//     wrapper:{
//         // CSS속성을 카멜표기법으로 작성
//         margin:8,
//         padding:8,
//         display:"flex",
//         flexDirection:"row",
//         border:"1px solid grey",
//         borderRadius:16,
//     },
//     imageContainer:{},
//     image:{
//         width:50,
//         height:50,
//         borderRadius:25,
//     },
//     contentContainer:{
//         marginLeft:8,
//         display:"flex",
//         flexDirection:"column",
//         justifyContent:"center",
//     },
//     nameText:{
//         color:"black",
//         fontSize:16,
//         fontWeight:"bold",
//     },
//     commentText:{
//         color:"black",
//         fontSize:16,
//     }
// }

// function Comment(props){
//     return (
//         <div className="wrapper">
//             <div style={styles.imageContainer}>
//                 <img src="https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png" style={styles.image}/>
//             </div>
//             <div style={styles.contentContainer}>
//                 <span style={styles.nameText}>{props.name}</span>
//                 <span style={styles.commentText}>
//                     {props.comment}
//                 </span>
//             </div>
//         </div>
//     )
// }
// export default Comment;