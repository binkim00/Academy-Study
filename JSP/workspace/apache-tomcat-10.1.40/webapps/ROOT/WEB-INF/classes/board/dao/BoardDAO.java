package board.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import board.dto.BoardDTO;
import common.DBConnPool;

public class BoardDAO extends DBConnPool{
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM tourist_notice";
		if(map.get("searchWord") != null) {
			query += " WHERE title "
					+" LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	public List<BoardDTO> selectList(Map<String, Object> map){
		List<BoardDTO> bbs = new Vector<>();
		String query = "SELECT * FROM tourist_notice";
		if(map.get("searchWord") != null) {
			query += " WHERE title "
					+" LIKE '%" + map.get("searchWord") + "%'";
		}
		query += " ORDER BY num DESC";
		System.out.println(query);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitCount(rs.getInt("visitcount"));
				bbs.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	public List<BoardDTO> selectListPaging(Map<String, Object> map) {
	    List<BoardDTO> bbs = new Vector<>();
	    String query = "SELECT * FROM ("
	                 + "  SELECT Tb.*, ROWNUM rNum FROM ("
	                 + "    SELECT * FROM tourist_notice";

	    if (map.get("searchWord") != null) {
	        query += " WHERE title LIKE '%" + map.get("searchWord") + "%'";
	    }

	    query += " ORDER BY num DESC"
	           + "  ) Tb"
	           + " ) WHERE rNum BETWEEN ? AND ?";

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setInt(1, (int) map.get("start"));
	        psmt.setInt(2, (int) map.get("end"));
	        rs = psmt.executeQuery();

	        while (rs.next()) {
	            BoardDTO dto = new BoardDTO();
	            dto.setNum(rs.getInt("num"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setPostDate(rs.getDate("postdate"));
	            dto.setId(rs.getString("id"));
	            dto.setVisitCount(rs.getInt("visitcount"));
	            bbs.add(dto);
	        }
	    } catch (Exception e) {
	        System.out.println("페이징 게시물 조회 중 예외 발생");
	        e.printStackTrace();
	    }

	    return bbs;
	}

	
	
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		try {
			String query ="INSERT INTO tourist_notice (num,title,content,id,visitcount)"
					+ "VALUES (seq_tourist_notice_num.NEXTVAL, ?, ?, ?, 0)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	public BoardDTO selectView(int num) {
		// BoardDTO를 선언하는 부분
		BoardDTO dto = new BoardDTO();
		// DB에서 사용할 쿼리를 작성한다
		String query = "SELECT *"
				+ " FROM tourist_notice "
				+ " WHERE num = ?";
		System.out.println(query);
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitCount(rs.getInt("visitcount"));
			}
		}catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	public void updateVisitCount(int num) {
		String query = " UPDATE tourist_notice SET "
				+ " visitcount = visitcount+1 "
				+ " WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, num);
			psmt.executeQuery();
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE tourist_notice SET title=?, content=? "
					+ "WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNum());
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("수정 처리 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	public int deleteBoard(int num) {
		int result = 0;
		try {
			String query = "DELETE FROM tourist_notice WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, num);
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("삭제 중 예외가 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
}








