package com.hanains.mysite.http.action.user;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;
import com.hanains.mysite.http.action.main.IndexAction;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)){
			action = new JoinFormAction();
		} else if("join".equals(actionName)){
			action = new JoinAction();
		} else if("joinsuccess".equals(actionName)){
			action = new JoinSuccessAction();
		} else if("loginform".equals(actionName)){
			action = new LoginFormAction();
		} else if("login".equals(actionName)){
			action = new LoginAction();
		} else if("logout".equals(actionName)){
			action = new LogoutAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}
