package chapter24;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Ex02_FileWrite2
{
    public static void main(String[] args)
    {
        OutputStream out = null;

        try 
        {
        	// 입출력 스트림은 예외처리를 하지 않으면 컴파일 에러가 발생하고
        	// 프로그램을 실행할 수 없음
            out = new FileOutputStream("data.txt");
            out.write(65);    // ASCII 코드 65 = 'A'
            //out.close();
        }
        catch (IOException e) 
        {
            
        }
        finally 
        // 입출력 스트림이 만들어져 있는지 확인
        // 스트림이 null인 상태로 close()를 실행하면 에러가 발생
        {
            if (out != null) 
            {
                try 
                {
                    out.close(); 
                }
                catch (IOException e2) 
                {

                }
            }
        }
    }
}
