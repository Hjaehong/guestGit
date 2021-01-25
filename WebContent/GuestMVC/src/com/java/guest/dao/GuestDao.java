package com.java.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.management.remote.JMXConnectionNotification;

import com.java.db.ConnectionProvider;
import com.java.db.JdbcClose;
import com.java.guest.dto.GuestDto;

public class GuestDao {
	private static GuestDao instance = new GuestDao();

	public static GuestDao guestinstance() {
		return instance;
	}

	public int insert(GuestDto guestDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int check = 0;

		try {
			String sql = "Insert into guest values(guest_num_seq.nextval,? ,?, ?, sysdate)";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, guestDto.getName());
			pstmt.setString(2, guestDto.getPassword());
			pstmt.setString(3, guestDto.getMessage());

			// ex
			check = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcClose.close(pstmt);
			JdbcClose.close(conn);
		}

		return check;

	}

	public ArrayList<GuestDto> getGuestList() {
		Connection conn = null;
		// 쿼리 명 전달
		PreparedStatement pstmt = null;
		// 결과 값 전달
		ResultSet rs = null;
		ArrayList<GuestDto> arrayList = null;

		try {
			String sql = "select * from guest order by num desc";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// next는 커서의 위치
			// 즉 커서 다음에 데이터가 있는지 없는지 확인하는 방법
			arrayList = new ArrayList<GuestDto>();
			while (rs.next()) {
				GuestDto guestDto = new GuestDto();
				guestDto.setNum(rs.getInt("num"));
				guestDto.setName(rs.getString("name"));
				guestDto.setPassword(rs.getString("password"));
				guestDto.setMessage(rs.getString("message"));
				guestDto.setWriteDate(rs.getTimestamp("write_date"));

				arrayList.add(guestDto);
			}

		} catch (Exception e) {

		} finally {
			JdbcClose.close(rs);
			JdbcClose.close(pstmt);
			JdbcClose.close(conn);
		}

		return arrayList;
	}

	public int delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int check = 0;

		try {
			String sql = "delete from guest where num=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			check = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcClose.close(pstmt);
			JdbcClose.close(conn);
		}
		return check;
	}

	public GuestDto upSelct(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestDto guestDto = null;
		
		try {	
			String sql = "select * from guest where num=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
				
			
			if(rs.next()) {
				guestDto=new GuestDto();
				guestDto.setNum(rs.getInt("num"));
				guestDto.setName(rs.getString("name"));
				guestDto.setPassword(rs.getString("password"));
				guestDto.setMessage(rs.getString("message"));
				guestDto.setWriteDate(rs.getTimestamp("write_date"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcClose.close(rs);
			JdbcClose.close(pstmt);
			JdbcClose.close(conn);
		}
		
		return guestDto;
	}

}
