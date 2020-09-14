package kr.mem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;

//@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		// 1.으아아아ㅏ
		String reqUrl=request.getRequestURI();
//		System.out.println(reqUrl);
		String ctxPath=request.getContextPath();
//		System.out.println(ctxPath);
		String command = reqUrl.substring(ctxPath.length());
		System.out.println(command);
		HandlerMapping mapping= new HandlerMapping();
		Controller controller = null;
		//으아아아
		//으아아아아
//		MemberDAO dao = new MemberDAO();
		controller = mapping.getController(command);
		String nextView = controller.requestHandle(request, response);
		
//		if(command.equals("/list.do")) {
//			controller=new MemberListController();
//			nextView =controller.requestHandle(request, response);
//		}else if(command.equals("/insert.do")) {
//			controller=new MemberInsertController();
//			nextView =controller.requestHandle(request, response);
//		}else if(command.equals("/insertForm.do")) {
//			controller=new MemberInsertFormController();
//			nextView =controller.requestHandle(request, response);
//		}else if(command.equals("/delete.do")) {
//			controller=new MemberDeleteController();
//			nextView =controller.requestHandle(request, response);
//		}
		//---------------------
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!=-1) {
				//indexOf -> �ش� ���ڿ��� �����ϸ� �ش� �ε��� ��ȯ ���ٸ� -1 ��ȯ
				String[] sp = nextView.split(":"); //sp[0]:sp[1]
				response.sendRedirect(sp[1]);
			}else {
				//�������� ���� ���ؽ�Ʈ ��Ī���� �̷������ ������ mvc1�� �� �����൵ �ȴ�.
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
	}

}
