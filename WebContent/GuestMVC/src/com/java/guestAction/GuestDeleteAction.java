package com.java.guestAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.CommandAction;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;

public class GuestDeleteAction implements CommandAction {

	@Override
	public String actionDo(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		
		int check=GuestDao.guestinstance().delete(num);
		System.out.println("check" + check);
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/guest/delete.jsp";
	}

}
