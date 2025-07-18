import { useState } from "react";

function Practice2(props) {
  const [id, setId] = useState("");
  const [pw, setPw] = useState("");
  const [pwTest, setPwTest] = useState("");
  const [message, setMessage] = useState("빈 칸을 모두 입력하세요.");
  const existingUsers = ["tester"];

  const handleChangeId = (event) => setId(event.target.value);
  const handleChangePw = (event) => setPw(event.target.value);
  const handleChangePwTest = (event) => setPwTest(event.target.value);

  const handleSubmit = (event) => {
    event.preventDefault();

    if (!id || !pw || !pwTest) {
      setMessage("빈 칸을 모두 입력하세요.");
      return;
    }

    if (pw !== pwTest) {
      setMessage("비밀번호가 일치하지 않습니다.");
      return;
    }

    if (existingUsers.includes(id)) {
      setMessage("이미 존재하는 계정입니다.");
      return;
    }

    setMessage("");
    alert(`회원가입 성공!\r\nID: ${id}`);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h1>REACTERS</h1>
      <h2>회원가입</h2>
      <p>
        <input
          type="text"
          value={id}
          onChange={handleChangeId}
          placeholder="아이디"
        />
      </p>
      <p>
        <input
          type="password"
          value={pw}
          onChange={handleChangePw}
          placeholder="비밀번호"
        />
      </p>
      <p>
        <input
          type="password"
          value={pwTest}
          onChange={handleChangePwTest}
          placeholder="비밀번호 확인"
        />
      </p>
      <p>{message}</p>
      <button type="submit">회원가입</button>
    </form>
  );
}

export default Practice2;
