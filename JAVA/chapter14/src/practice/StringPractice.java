package practice;

public class StringPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//문제 1
//		주민등록번호의 연월일을 각각의 변수에 저장하세요.
		String str = "990417-1234567";
		String year = str.substring(0,2);
//		Integer.parseInt("문자열") : 문자열을 int자료형으로 변환
		int intYear = Integer.parseInt(year);
//		50보다 크면 19를 더하고 작으면 20을 더하는 if문
		if(intYear>50) {
			year = "19"+year;
		}else {
			year = "20"+year;
		}
		String month = str.substring(2,4);
		String day = str.substring(4,6);
		System.out.println(year+"년"+month+"월"+day+"일 이 생일입니다.");
		
//		문제2
//		a/b/c/d를 a:b:c:d 로 바꾸어 저장하세요.
		String str2 = "a/b/c/d";
		String[] strArr = str2.split("/");
		String str3 = "";
		for(int i=0; i<strArr.length; i++) {
			str3+=strArr[i];
			if(i<strArr.length-1) {
				str3+=":";
			}
		}
		System.out.println(str3);
		
//		replace(변경전문자열, 변경후문자열) : 특정 문자열을 변경하는 메서드
		System.out.println(str2.replace("/", ":"));
	}

}







