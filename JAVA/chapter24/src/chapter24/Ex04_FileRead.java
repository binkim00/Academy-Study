package chapter24;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ex04_FileRead
{
    public static void main(String[] args)
    {
        try (InputStream in = new FileInputStream("data.txt"))
        {
        	// read() : 글자 하나를 읽어 int 값으로 돌려주는 메서드
            int dat = in.read();
            //int값으로 출력되어 숫자로 나옴
            System.out.println(dat);
            //int값을 char자료형으로 바꿔서 출력
            System.out.printf("%c \n", dat);
        } 
        catch(IOException e) 
        {
            e.printStackTrace();       
        }      
    }
}
