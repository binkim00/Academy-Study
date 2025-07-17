// html 파일에 직접 react를 적용하는 방식
function MyButton(props){
    const [isClicked, setIsClicked] = React.useState(false);

    return React.createElement(
        'button',
        { onClick : () => setIsClicked(!isClicked) },
        isClicked ? 'Clicked!' : 'Click here!'
    )
}

// root : index.html에 있는 root 태그 안의 내용이 바뀌도록 설정
const domContainer = document.querySelector('#root');
// root태그를 React에서 사용하는 root로 설정
const root = ReactDOM.createRoot(domContainer);
// root태그안에 React에서 구현할 내용을...
root.render(React.createElement(MyButton));