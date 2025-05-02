package chapter24;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Ex09_TextRead
{
    public static void main(String[] args)
    {
        try (Reader in = new FileReader("text.txt"))
        {
            int ch;

            while(true) 
            {
            	// 파일의 텍스트를 한글자씩 저장
                ch = in.read();
                // -1 은 글자가 없을 때 출력되는 값
                if (ch == -1)
                    break;
                // 읽은 글자 출력하는 부분
                System.out.print((char)ch);
            }
        }
        catch(IOException e) 
        {
            e.printStackTrace();       
        }
    }
}
