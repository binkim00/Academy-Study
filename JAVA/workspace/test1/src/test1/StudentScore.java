package test1;

public class StudentScore {
	
	 public static void main(String[] args) {
		 int[][] scores = {
			    {80,60,70},
			    {90,95,80},
			    {75,80,100},
			    {80,70,95},
			    {100,65,80}
			};
	
		 System.out.println("학생번호     국어     영어    수학     총점     평균");
	     System.out.println("=============================================");
		 
		 for(int i = 0; i < scores.length; i++) {
			 int kor = scores[i][0];
	            int eng = scores[i][1];
	            int math = scores[i][2];
	            int total = kor + eng + math;
	            int avg = total / 3; 

	            System.out.printf("%d번 학생:  %4d  %5d  %5d  %6d  %7d\n",
	                              (i + 1), kor, eng, math, total, avg);
		 }

	 }
}
