package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public DBConnPool() {
		try {
//			컨텐스트 초기화 
			Context initCtx = new InitialContext();
//			컨텍스트에 프로젝트 환경 설정
			Context ctx = (Context)initCtx.lookup("java:comp/env");
//			server.xml에 작성한 설정을 가지고 데이터소스 생성
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
//			Connection 생성
			con = source.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
			if(psmt != null) {psmt.close();}
			if(con != null) {con.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}









