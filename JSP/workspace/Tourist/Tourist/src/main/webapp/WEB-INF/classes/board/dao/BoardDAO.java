package board.dao;

import java.util.*;

import board.dto.BoardDTO;

import common.DBConnPool;

public class BoardDAO extends DBConnPool {

    // 게시글 목록 조회
    public List<BoardDTO> selectList() {
        List<BoardDTO> boardList = new ArrayList<>();

        String query = "SELECT * FROM tourist_board ORDER BY num DESC";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setNum(rs.getString("NUM"));
                dto.setTitle(rs.getString("TITLE"));
                dto.setContent(rs.getString("CONTENT"));
                dto.setId(rs.getString("ID"));
                dto.setPostdate(rs.getDate("POSTDATE"));
                dto.setVisitcount(rs.getInt("VISITCOUNT"));

                boardList.add(dto);
            }
        } catch (Exception e) {
            System.out.println("게시판 목록 조회 중 예외 발생");
            e.printStackTrace();
        }

        return boardList;
    }

	    // 게시글 상세 보기 (1건)
	    public BoardDTO selectView(String num) {
	        BoardDTO dto = new BoardDTO();
	        try {
	            String query = 
	                "SELECT b.*, m.name " +
	                "FROM tourist_board b " +
	                "JOIN tourist_member m ON b.id = m.id " +
	                "WHERE b.num = ?";
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, num);
	            rs = psmt.executeQuery();
	            if (rs.next()) {
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	                dto.setContent(rs.getString("content"));
	                dto.setId(rs.getString("id"));
	                dto.setName(rs.getString("name"));  // ✅ 작성자 이름
	                dto.setPostdate(rs.getDate("postdate"));
	                dto.setVisitcount(rs.getInt("visitcount"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return dto;
	    }


    // 조회수 증가
    public void updateVisitCount(String num) {
        String query = "UPDATE tourist_board SET visitcount=visitcount+1 WHERE num=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, num);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("조회수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }
    
	 // 이전글 번호 구하기
	    public String getPrevNum(String currentNum) {
	        String query = "SELECT MAX(num) FROM tourist_board WHERE num < ?";
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, currentNum);
	            rs = psmt.executeQuery();
	            if (rs.next()) return rs.getString(1);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	    // 다음글 번호 구하기
	    public String getNextNum(String currentNum) {
	        String query = "SELECT MIN(num) FROM tourist_board WHERE num > ?";
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, currentNum);
	            rs = psmt.executeQuery();
	            if (rs.next()) return rs.getString(1);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
    
	 // 이전글 DTO
	    public BoardDTO getPrevPost(String currentNum) {
	        BoardDTO dto = null;
	        String query = "SELECT * FROM tourist_board WHERE num < ? ORDER BY num DESC FETCH FIRST 1 ROWS ONLY";
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, currentNum);
	            rs = psmt.executeQuery();
	            if (rs.next()) {
	                dto = new BoardDTO();
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return dto;
	    }

	    // 다음글 DTO
	    public BoardDTO getNextPost(String currentNum) {
	        BoardDTO dto = null;
	        String query = "SELECT * FROM tourist_board WHERE num > ? ORDER BY num ASC FETCH FIRST 1 ROWS ONLY";
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, currentNum);
	            rs = psmt.executeQuery();
	            if (rs.next()) {
	                dto = new BoardDTO();
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return dto;
	    }

	 // 최근 공지사항 5개만 가져오는 메서드
	    public List<BoardDTO> selectRecentNotices(int count) {
	        List<BoardDTO> boardList = new ArrayList<>();
	        String query = "SELECT * FROM (SELECT * FROM TOURIST_BOARD ORDER BY postdate DESC) WHERE ROWNUM <= ?";

	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setInt(1, count);
	            rs = psmt.executeQuery();

	            while (rs.next()) {
	                BoardDTO dto = new BoardDTO();
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	                dto.setId(rs.getString("id"));
	                dto.setPostdate(rs.getDate("postdate"));
	                dto.setVisitcount(rs.getInt("visitcount"));
	                boardList.add(dto);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return boardList;
	    }
	    
	 // 글쓰기 메서드
	    public int insertWrite(BoardDTO dto) {
	        int result = 0;
	        try {
	            String sql = "INSERT INTO tourist_board (num, title, content, id, postdate, visitcount) "
	                       + "VALUES (seq_tourist_board_num.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
	            psmt = con.prepareStatement(sql);
	            psmt.setString(1, dto.getTitle());
	            psmt.setString(2, dto.getContent());
	            psmt.setString(3, dto.getId());
	            result = psmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	    // 검색어 메서드
	    public List<BoardDTO> searchList(String keyword) {
	        List<BoardDTO> list = new ArrayList<>();
	        try {
	            String sql = "SELECT * FROM tourist_board WHERE title LIKE ? ORDER BY num DESC";
	            psmt = con.prepareStatement(sql);
	            psmt.setString(1, "%" + keyword + "%");
	            rs = psmt.executeQuery();
	            while (rs.next()) {
	                BoardDTO dto = new BoardDTO();
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	                dto.setContent(rs.getString("content"));
	                dto.setId(rs.getString("id"));
	                dto.setPostdate(rs.getDate("postdate"));
	                dto.setVisitcount(rs.getInt("visitcount"));
	                list.add(dto);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	 // ✅ 페이지별 게시글 목록 조회
	    public List<BoardDTO> selectListPage(int start, int end) {
	        List<BoardDTO> boardList = new ArrayList<>();

	        String query = "SELECT * FROM ("
	                     + " SELECT Tb.*, ROWNUM rNum FROM ("
	                     + "   SELECT * FROM tourist_board ORDER BY num DESC"
	                     + " ) Tb WHERE ROWNUM <= ?"
	                     + ") WHERE rNum >= ?";

	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setInt(1, end);
	            psmt.setInt(2, start);
	            rs = psmt.executeQuery();

	            while (rs.next()) {
	                BoardDTO dto = new BoardDTO();
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	                dto.setContent(rs.getString("content"));
	                dto.setId(rs.getString("id"));
	                dto.setPostdate(rs.getDate("postdate"));
	                dto.setVisitcount(rs.getInt("visitcount"));

	                boardList.add(dto);
	            }
	        } catch (Exception e) {
	            System.out.println("페이지별 게시글 조회 중 예외 발생");
	            e.printStackTrace();
	        }

	        return boardList;
	    }
	    
	 // ✅ 전체 게시글 수 반환
	    public int getTotalCount() {
	        int total = 0;
	        try {
	            String query = "SELECT COUNT(*) FROM tourist_board";
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(query);
	            if (rs.next()) total = rs.getInt(1);
	        } catch (Exception e) {
	            System.out.println("게시글 수 조회 중 예외 발생");
	            e.printStackTrace();
	        }
	        return total;
	    }


	    
}
