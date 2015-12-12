package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		long group = Long.parseLong(request.getParameter("group"));
		long order = Long.parseLong(request.getParameter("order"));
		long depth = Long.parseLong(request.getParameter("depth"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setGroup_no(group);
		vo.setOrder_no(order+1);
		vo.setDepth(depth+1);
		if(session!=null){
			vo.setMember_no(((UserVo)session.getAttribute("authUser")).getNo());
		}
		dao.insert(vo);
		
		HttpUtil.redirect(response, "/mysite/board?pg=1");		
	}

}
