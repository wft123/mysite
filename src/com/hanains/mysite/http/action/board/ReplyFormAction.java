package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("group", request.getParameter("group"));
		request.setAttribute("order", request.getParameter("order"));
		request.setAttribute("depth", request.getParameter("depth"));
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/replyform.jsp");
	}

}
