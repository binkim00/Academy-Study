package program.dao;

import java.util.*;
import java.sql.*;

import common.DBConnPool;
import program.dto.ProgramDTO;

public class ProgramDAO extends DBConnPool {

	public List<ProgramDTO> selectAllPrograms() {
	    List<ProgramDTO> programs = new Vector<>();

	    String query = "SELECT \"NO\", \"TITLE\", \"TEXT\", \"SUBTEXT\", \"SCHEDULE\", \"IMG\" FROM \"TOURIST_PROGRAM\" ORDER BY \"NO\" DESC";

	    try {
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            ProgramDTO dto = new ProgramDTO();
	            dto.setProgramId(rs.getInt("NO"));
	            dto.setTitle(rs.getString("TITLE"));
	            dto.setDescription(rs.getString("TEXT"));
	            dto.setSubtext(rs.getString("SUBTEXT"));
	            dto.setSchedule(rs.getString("SCHEDULE"));
	            dto.setImagePath(rs.getString("IMG"));

	            programs.add(dto);
	        }

	    } catch (Exception e) {
	        System.out.println("프로그램 목록 조회 중 예외 발생");
	        e.printStackTrace();
	    }

	    return programs;
	}


}
