package com.java.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.CommandAction;
import com.java.guestAction.GuestDeleteAction;
import com.java.guestAction.GuestUpdateAction;
import com.java.guestAction.GuestWriteAction;
import com.java.guestAction.GuestWriteOkAction;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> commandMap=new HashMap<String, Object>();
    
	
   

	
	@Override
	public void init() throws ServletException {
		// �� �Լ��� ���� ���� ȣ��ȴ�.
		GuestWriteAction guestWrite = new GuestWriteAction();
		commandMap.put("/guest/write.do", guestWrite);
		
		GuestWriteOkAction guestWriteOk = new GuestWriteOkAction();
		commandMap.put("/guest/writeOk.do", guestWriteOk);
		
		GuestDeleteAction guestDelete = new GuestDeleteAction();
		commandMap.put("/guest/delete.do", guestDelete);
		
		GuestUpdateAction guestUpdate = new GuestUpdateAction();
		commandMap.put("/guest/update.do", guestUpdate);
		
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getServletPath();
		System.out.println(cmd);
		
		
		String viewPage = null;
		try {
			// �ش� Ŭ������ �Ѿ�ش�
			CommandAction command = (CommandAction) commandMap.get(cmd);
			
			viewPage = command.actionDo(request, response);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		if(viewPage != null) {
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
