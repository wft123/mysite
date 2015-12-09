package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.getView(no);
		dao.upCount(no);

		request.setAttribute("vo", vo);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/view.jsp");		
	}

}
