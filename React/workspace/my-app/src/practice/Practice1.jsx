// React의 훅(Hook)인 useState와 useMemo를 가져옵니다.
// - useState: 컴포넌트에서 상태값을 저장하고 바꾸는 기능
// - useMemo: 어떤 값이 바뀔 때만 결과를 "기억"해두는 기능 (성능 최적화용)
import { useState, useMemo } from 'react';

// 가위(0), 바위(1), 보(2)에 해당하는 이미지 파일을 import합니다.
// 이 이미지는 JSX에서 <img src={...} /> 형태로 사용됩니다.
import img0 from '../img/0.png'; // 가위 이미지
import img1 from '../img/1.png'; // 바위 이미지
import img2 from '../img/2.png'; // 보 이미지

// 이 컴포넌트에 사용할 CSS를 불러옵니다.
import './Practice1.css';

// 리액트 컴포넌트: 하나의 화면 단위를 정의하는 함수입니다.
function Practice1() {
  /**
   * 📌 useState는 리액트에서 상태(state)를 만들 때 사용하는 훅입니다.
   * 상태값이 바뀌면 자동으로 화면이 다시 렌더링됩니다.
   */

  const [player, setPlayer] = useState(1);         // 플레이어의 선택 (0~2)
  const [computer, setComputer] = useState(1);     // 컴퓨터의 선택 (0~2)
  const [history, setHistory] = useState([]);      // 게임 기록을 저장할 배열
  const [gameStarted, setGameStarted] = useState(false); // 게임이 시작되었는지 여부

  /**
   * ✊✌🖐 선택지와 이미지 배열
   * - 인덱스를 기준으로 텍스트(이모지)와 이미지가 연결됩니다.
   */
  const choices = ['✌', '👊', '🖐']; // 0: 가위, 1: 바위, 2: 보
  const images = [img0, img1, img2]; // 이미지도 0~2 순서로 대응

  /**
   * 승패 결과를 계산하는 함수
   * - player와 computer는 각각 0~2 값 (가위, 바위, 보)
   * - 게임 규칙:
   *    (p == c)       → 무승부
   *    (p + 1) % 3 == c → 패배
   *    나머지는 승리
   */
  const getResultText = (p, c) => {
    if (p === c) return '비겼습니다.';
    if ((p + 1) % 3 === c) return '졌습니다.';
    return '이겼습니다.';
  };

  /**
   * ✨ 버튼 클릭 시 호출되는 함수
   * - 사용자 선택을 저장
   * - 컴퓨터는 랜덤 선택
   * - 결과 계산 및 기록 저장
   */
  const onClickResult = (playerChoice) => {
    const computerChoice = Math.floor(Math.random() * 3); // 0~2 랜덤

    setPlayer(playerChoice);         // 사용자 선택 저장
    setComputer(computerChoice);     // 컴퓨터 선택 저장
    setGameStarted(true);            // 게임 시작 표시

    const resultText = getResultText(playerChoice, computerChoice); // 결과 계산
    const newItem = `${choices[playerChoice]} vs ${choices[computerChoice]} ${resultText}`;

    // 이전 기록에 새 결과를 추가 (리액트 상태는 직접 수정하면 안 되므로 spread 연산자 사용)
    setHistory((prev) => [...prev, newItem].slice());
  };

  /**
   * 🧠 useMemo는 특정 값이 바뀔 때만 다시 계산되도록 "메모이제이션" 합니다.
   * - player, computer, gameStarted가 바뀔 때만 resultText 계산
   * - 성능 최적화뿐만 아니라 의도치 않은 재계산을 방지합니다.
   */
  const resultText = useMemo(() => {
    if (!gameStarted) return '게임을 시작하세요';
    return getResultText(player, computer);
  }, [player, computer, gameStarted]);

  /**
   * JSX 반환
   * - HTML 비슷하게 생긴 React의 JSX 문법
   * - 조건에 따라 내용 바뀌고, 반복문(map)도 사용 가능
   */
  return (
    <div className="game-container">
      {/* 결과 메시지 출력 */}
      <h2 className="result-text">{resultText}</h2>

      {/* 사용자와 컴퓨터 이미지 표시 영역 */}
      <div className="image-area">
        <div>
          <img src={images[player]} alt="player" width="150" />
          {/* 이겼을 경우에만 'win' 표시 */}
          <div>{gameStarted && getResultText(player, computer) === '이겼습니다.' ? 'win' : ''}</div>
        </div>

        <div className="vs">VS</div>

        <div>
          <img src={images[computer]} alt="computer" width="150" />
          {/* 졌을 경우에만 'lose' 표시 */}
          <div>{gameStarted && getResultText(player, computer) === '졌습니다.' ? 'lose' : ''}</div>
        </div>
      </div>

      {/* ✌ 👊 🖐 선택 버튼 */}
      <div className="button-area">
        {choices.map((c, idx) => (
          <button
            key={idx}
            onClick={() => onClickResult(idx)} // 클릭 시 사용자 선택
            className="choice-button"
          >
            {c}
          </button>
        ))}
      </div>

      {/* 게임 기록 출력 (map을 사용한 반복 렌더링) */}
      <ul className="history-list">
        {history.map((item, idx) => (
          <li key={idx}>{item}</li>
        ))}
      </ul>
    </div>
  );
}

// 이 컴포넌트를 다른 곳에서 사용할 수 있도록 내보냅니다.
export default Practice1;
