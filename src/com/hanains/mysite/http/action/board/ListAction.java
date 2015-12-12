package com.hanains.mysite.http.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		int page =1;
		if(request.getParameter("pg")!=null)	page = Integer.parseInt(request.getParameter("pg"));
		String kwd = request.getParameter("kwd");
		List<BoardVo> list = dao.getListPage(page,kwd);
		request.setAttribute("list", list);
		request.setAttribute("boardSize", dao.getBoardSize(kwd));
		request.setAttribute("pageSize", dao.PAGE_ROW);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
