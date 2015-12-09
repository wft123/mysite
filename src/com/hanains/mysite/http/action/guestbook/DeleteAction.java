package com.hanains.mysite.http.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.GuestBookDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		String password = request.getParameter("password");
		GuestBookDao dao = new GuestBookDao();
		int result = dao.delete(no, password);
		PrintWriter out = response.getWriter();
		
		if(result>0){
			out.println("<script>");
			out.println("alert('삭제되었습니다.');");
			out.println("</script>");
			
			HttpUtil.redirect(response, "/mysite/guestbook");
		}else{
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
		}
	}

}
