package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller {
	// 포조의 메소드 이름 통일 -> 호출때마다 각자의 메소드를 찾기 번거러우므로

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO dao= new MemberDAO();
		ArrayList<MemberVO> list = dao.memberAllList();
		request.setAttribute("list", list);
		return "member/memberList.jsp";
	}
	
//	@Override
//	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		MemberDAO dao=new MemberDAO();
//		ArrayList<MemberVO> list = dao.memberAllList();
//		//객체바인딩
//		request.setAttribute("list", list);
//		//View -> member/memberList.jsp
//		return "member/memberList.jsp";
//	}
	
	
	
	
	
	
//	public void requestHandle() {
//		//1. 전체리스트를 가져오기
//		MemberDAO dao=new MemberDAO();
//		ArrayList<MemberVO> list = dao.memberAllList();
//		//2. 객체바인딩
//		request.
////		// member/memberList.jsp
////		request.setAttribute("list", list);
////		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
////		rd.forward(request, response);
//	}
}
