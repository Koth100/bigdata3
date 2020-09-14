package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller {
	// ������ �޼ҵ� �̸� ���� -> ȣ�⶧���� ������ �޼ҵ带 ã�� ���ŷ���Ƿ�

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
//		//��ü���ε�
//		request.setAttribute("list", list);
//		//View -> member/memberList.jsp
//		return "member/memberList.jsp";
//	}
	
	
	
	
	
	
//	public void requestHandle() {
//		//1. ��ü����Ʈ�� ��������
//		MemberDAO dao=new MemberDAO();
//		ArrayList<MemberVO> list = dao.memberAllList();
//		//2. ��ü���ε�
//		request.
////		// member/memberList.jsp
////		request.setAttribute("list", list);
////		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
////		rd.forward(request, response);
//	}
}
