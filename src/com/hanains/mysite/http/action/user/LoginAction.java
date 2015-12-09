package com.hanains.mysite.http.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password"	);
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email, password);
		
		if(vo==null){
			HttpUtil.redirect(response, "/mysite/user?a=loginform&result=fail");
			return;
		}
		
		//로그인 성공
		HttpSession session = request.getSession(true);
		if(session!=null){
			session.setAttribute("authUser", vo);
		}
		HttpUtil.redirect(response, "/mysite/main");
		
	}

}
