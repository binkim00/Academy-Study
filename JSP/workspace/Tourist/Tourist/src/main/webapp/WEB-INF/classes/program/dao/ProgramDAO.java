package program.dao;

import java.util.*;
import java.sql.*;

import program.dto.ProgramDTO;
import common.DBConnPool;

public class ProgramDAO extends DBConnPool {

	public List<ProgramDTO> selectAll() {
	    List<ProgramDTO> list = new ArrayList<>();

	    String sql = "SELECT * FROM tourist_program ORDER BY id";

	    try {
	        psmt = con.prepareStatement(sql);
	        rs = psmt.executeQuery();

	        while (rs.next()) {
	            ProgramDTO dto = new ProgramDTO();
	            dto.setId(rs.getInt("id"));
	            dto.setIdx(rs.getString("idx"));
	            dto.setTitle(rs.getString("title"));
	            dto.setText(rs.getString("text"));
	            dto.setSubtext(rs.getString("subtext"));
	            dto.setSchedule(rs.getString("schedule"));
	            dto.setImg(rs.getString("img"));

	            list.add(dto);
	        }
	    } catch (Exception e) {
	        System.out.println("관광 프로그램 목록 조회 중 예외 발생");
	        e.printStackTrace();
	    }

	    return list;
	}

    
    public ProgramDTO selectProgram(String idx) {
        ProgramDTO dto = null;

        try {
            String query = "SELECT * FROM tourist_program WHERE idx=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto = new ProgramDTO();
                dto.setId(rs.getInt("id"));
                dto.setIdx(rs.getString("idx"));
                dto.setTitle(rs.getString("title"));
                dto.setText(rs.getString("text"));
                dto.setSubtext(rs.getString("subtext"));
                dto.setSchedule(rs.getString("schedule"));
                dto.setImg(rs.getString("img"));
            }

        } catch (Exception e) {
            System.out.println("selectProgram() 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return dto;
    }

    public ProgramDTO selectProgramById(String id) {
        ProgramDTO dto = null;
        try {
            String query = "SELECT * FROM tourist_program WHERE id=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, id);
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto = new ProgramDTO();
                dto.setId(rs.getInt("id"));
                dto.setTitle(rs.getString("title"));
                dto.setText(rs.getString("text"));
                dto.setSubtext(rs.getString("subtext"));
                dto.setSchedule(rs.getString("schedule"));
                dto.setImg(rs.getString("img"));
                dto.setArea(rs.getString("area"));
                dto.setPrice(rs.getInt("price"));
                dto.setContent(rs.getString("content"));
            }
        } catch (Exception e) {
            System.out.println("selectProgramById() 오류: " + e.getMessage());
        }

        return dto;
    }



}
