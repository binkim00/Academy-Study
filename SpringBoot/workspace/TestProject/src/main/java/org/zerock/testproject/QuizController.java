package org.zerock.testproject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {


    @GetMapping("/quiz")
    // ResponseEntity<돌려줄 값의 자료형> : Response에 응답status와
    // 데이터, 헤더 등을 설정하여 반환할 수 있음
    public ResponseEntity<String> quiz(@RequestParam("code") int code) {
        switch (code) {
            case 1:
                return ResponseEntity.created(null).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code) {


        switch (code.value()) {
            case 1:
                return ResponseEntity.status(403).body("Forbidden!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }
}
// DTO를 편하게 작성하기 위한 기능, 테스트용도로 데이터전달시 사용
// 기본생성자는 만들어지지 않음
// setter메서드는 만들어지지 않음 => recode는 불변, 한번 설정한 값은 변경할 수 없음
// getter, 모든 속성을 설정하는 생성자, toString, equals, hashCode을 만들어주는 기능
record Code(int value) {}
class CodeDTO{
    private final int value;
    public CodeDTO(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    @Override
    public String toString() {
        return "Code[value="+value+"]";
    }
}