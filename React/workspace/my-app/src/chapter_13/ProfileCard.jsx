import Card from "./Card";

function ProfileCard(props){
    return (
        <Card title="Inje Lee" backgroundColor="#4ea04e">
            {/* Card컴포넌트에 children으로 전달되는 태그 두 개 */}
            <p>안녕하세요, 소플입니다.</p>
            <p>저는 리액트를 사용해서 개발하고 있습니다.</p>
        </Card>
    );
}
export default ProfileCard;