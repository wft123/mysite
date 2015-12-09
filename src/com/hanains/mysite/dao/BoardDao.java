package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hanains.http.util.DBClose;
import com.hanains.http.util.DBConnect;
import com.hanains.mysite.vo.BoardVo;

public class BoardDao {
	DBConnect dbconnect = null;
	String sql="";
	
	public BoardDao(){
		dbconnect = new DBConnect();
	}
	
	public void insert(BoardVo vo){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		
		String sql ="insert into board values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE )";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getMember_no());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public BoardVo getView(long no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		
		String sql = "select no, title, content, member_no from board where no="+no;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setMember_no(rs.getLong(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		return vo;
	}
	
	public void upCount(long no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update board set view_cnt = view_cnt + 1 where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public void modify(BoardVo vo){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update board set title=?, content=? where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public void delete(long no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from board where no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.no, ");
		sql.append("a.title, ");
		sql.append("a.member_no, ");
		sql.append("b.name as member_name, ");
		sql.append("a.view_cnt, ");
		sql.append("to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') ");
		sql.append("from board a, ");
		sql.append("member b ");
		sql.append("where a.member_no = b.no ");
		sql.append("order by a.reg_date desc");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setMember_no(rs.getLong(3));
				vo.setMember_name(rs.getString(4));
				vo.setView_cnt(rs.getLong(5));
				vo.setReg_date(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		
		return list;
	}
	
}
