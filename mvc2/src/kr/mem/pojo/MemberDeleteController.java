package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;

public class MemberDeleteController implements Controller{

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=null;
		String cpath=request.getContextPath();
		
		MemberDAO dao = new MemberDAO();
		int num=Integer.parseInt(request.getParameter("num"));
		int cnt = dao.memberDelete(num);
		if(cnt>0) {
			page="redirect:"+cpath+"/list.do";
		}else {
			throw new ServletException("error");
		}
		
		return page;
	}



}
