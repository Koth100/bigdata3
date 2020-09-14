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
		// 1.어떤요청인지 파악하는 작업 -> *.do
		String reqUrl=request.getRequestURI();
//		System.out.println(reqUrl);
		String ctxPath=request.getContextPath();
//		System.out.println(ctxPath);
		String command = reqUrl.substring(ctxPath.length());
		System.out.println(command);
		HandlerMapping mapping= new HandlerMapping();
		Controller controller = null;
		//각 요청에 따라 처리하기
		//핸들러 매핑
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
				//indexOf -> 해당 문자열이 존재하면 해당 인덱스 반환 없다면 -1 반환
				String[] sp = nextView.split(":"); //sp[0]:sp[1]
				response.sendRedirect(sp[1]);
			}else {
				//포워딩은 기존 콘텍스트 명칭에서 이루어지기 때문에 mvc1을 안 붙혀줘도 된다.
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
	}

}
