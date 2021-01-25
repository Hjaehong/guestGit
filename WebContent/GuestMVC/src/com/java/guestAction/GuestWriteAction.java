package com.java.guestAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.CommandAction;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;

public class GuestWriteAction implements CommandAction {

	@Override
	public String actionDo(HttpServletRequest request, HttpServletResponse response)throws Throwable {
		// �۾��� ���� �̵��� �ϱ� ������ ���� �۾��� �ʿ� ����
		// ����ڰ� index.jsp�� ������ �ٸ� ���Ͽ� ������ ���ϰ�
//		System.out.println("OK");
		
		// WriteAction --> write.jsp ȣ�� �� �� �� / �ۼ�, ����Ʈ ��� �� �ش�.
		// �������� �Խù� ��� 10�� -> 1page 1-10 2page 11-20
		ArrayList<GuestDto> guestList = GuestDao.guestinstance().getGuestList();
		
		if(guestList != null) {
			System.out.println("������" + guestList.size());
			// write.jsp �������� �ش� ���� ��������
			request.setAttribute("guestList", guestList);
		}
		
		
		return"/WEB-INF/views/guest/write.jsp";
	}

}
