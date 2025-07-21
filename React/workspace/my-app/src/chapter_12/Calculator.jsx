import { useState } from "react";
import TemperatureInput from "./TemperatureInput";

function BoilingVerdict(props){
    if(props.celsius>=100){
        return <p>물이 끓습니다.</p>
    }
    return <p>물이 끓지 안습니다.</p>
}
function toCelsius(fahrenheit){
    return ((fahrenheit - 32)*5)/9;
}
function toFahrenheit(celsius){
    return (celsius*9)/5+32;
}
// 섭씨, 화씨 변환을 둘다 실행할 수 있는 함수
function tryConvert(temperature,convert){
    // temperature를 숫자 데이터로 변환
    const input = parseFloat(temperature);
    // 변환한 데이터가 숫자인지 아닌지 확인
    // isNaN(변수) : Not a Number : 숫자가 아니면 true가 출력되는 함수
    if(Number.isNaN(input)){
        return "";
    }
    // 섭씨면 화씨로 화씨면 섭씨로 변환
    const output = convert(input);
    // 소수점 제거
    const rounded = Math.round(output * 1000) / 1000;
    // 문자열로 변경하여 반환
    return rounded.toString();
}
function Calculator(props){
    const [temperature, setTemperature] = useState("");
    const [scale, setScale] = useState("c");
    const handleCelsiusChange = (temperature)=>{
        setTemperature(temperature);
        setScale("c");
    }
    const handleFahrenheitChange = (temperature) =>{
        setTemperature(temperature);
        setScale("f");
    }
    // change함수가 실행될때마다 리렌더에의해 온도 계산이 실행됨
    const celsius = scale === "f" ? tryConvert(temperature, toCelsius) : temperature;
    const fahrenheit = scale === "c" ? tryConvert(temperature, toFahrenheit): temperature;
    return (
        <div>
            <TemperatureInput 
            scale="c" 
            temperature={celsius}
            onTemperatureChange={handleCelsiusChange}/>
            <TemperatureInput 
            scale="f" 
            temperature={fahrenheit}
            onTemperatureChange={handleFahrenheitChange}/>
            <BoilingVerdict celsius={parseFloat(celsius)}/>
        </div>
    );
}
export default Calculator;