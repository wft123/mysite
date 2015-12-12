package com.hanains.mysite.http.action.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setGroup_no(dao.getMaxGroup()+1);
		vo.setOrder_no(1);
		vo.setDepth(0);
		if(session!=null){
			vo.setMember_no(((UserVo)session.getAttribute("authUser")).getNo());
		}
		
		dao.insert(vo);
		
		HttpUtil.redirect(response, "/mysite/board");		
	}

}
