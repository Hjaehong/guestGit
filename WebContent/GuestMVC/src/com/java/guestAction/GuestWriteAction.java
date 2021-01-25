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
		// 작업이 없이 이동만 하기 때문에 따로 작업이 필요 없다
		// 사용자가 index.jsp를 제외한 다른 파일에 접근을 못하게
//		System.out.println("OK");
		
		// WriteAction --> write.jsp 호출 해 줄 때 / 작성, 리스트 출력 해 준다.
		// 페이지당 게시물 출력 10개 -> 1page 1-10 2page 11-20
		ArrayList<GuestDto> guestList = GuestDao.guestinstance().getGuestList();
		
		if(guestList != null) {
			System.out.println("사이즈" + guestList.size());
			// write.jsp 페이지에 해당 값을 가져간다
			request.setAttribute("guestList", guestList);
		}
		
		
		return"/WEB-INF/views/guest/write.jsp";
	}

}
