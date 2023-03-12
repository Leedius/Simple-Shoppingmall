package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.membership.MemberImpl;

import dto.MemberDTO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("*.me")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//멤버 서비스를 사용할 수 있게 만드는 객체 생성
	//자료형을 인터페이스로 만들도록 한다.
	MemberService memberService = new MemberServiceImpl();
	
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//어떤 페이지에서 요청이 왓는지 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		
		
		//응답 페이지
		String page = "view/template/template.jsp";
		boolean isRedirect = false;
		
		//내용부 페이지
		String contentPage = "";
		
		//회원가입 페이지로 이동
		if(command.equals("/joinForm.me")) {
			contentPage = "member/join_form.jsp";
		}
		
		//회원가입
		if(command.equals("/join.me")) {
			String memId = request.getParameter("memId");
			String memPw = request.getParameter("memPw");
			String memName = request.getParameter("memName");
			String gender = request.getParameter("gender");
			String addr = request.getParameter("addr");
			String addrDetail = request.getParameter("addrDetail");
			String[] memTell = request.getParameterValues("memTell");
			String[] memEmail = request.getParameterValues("memEmail");
			
			MemberDTO memberDTO = new MemberDTO();
			
			memberDTO.setMemId(memId);
			memberDTO.setMemPw(memPw);
			memberDTO.setMemName(memName);
			memberDTO.setGender(gender);
			memberDTO.setAddr(addr);
			memberDTO.setAddrDetail(addrDetail);
			memberDTO.setMemTell(memTell);
			memberDTO.setMemEmail(memEmail);
			
			memberService.joinMember(memberDTO);
			
			isRedirect = true;
			page = "loginForm.me";
			
		}
		
		//로그인 페이지로 이동
		if(command.equals("/loginForm.me")) {
		
			contentPage = "member/login_form.jsp";
		}
		
		//로그인 기능
		if(command.equals("/login.me")) {
			String memId = request.getParameter("memId");
			String memPw = request.getParameter("memPw");
			
			//로그인 정보를 저장할 객체 생성
			MemberDTO memberDTO = new MemberDTO();
			
			//로그인 정보 저장
			memberDTO.setMemId(memId);
			memberDTO.setMemPw(memPw);
			
			//로그인 쿼리 실행
			memberDTO = memberService.login(memberDTO);
			
			//로그인이 성공했으면 세션에 로그인한 사람의 정보를 저장
			if(memberDTO != null) {
				//1.세션 객체 생성
				HttpSession session = request.getSession();
				
				//2.세션에 데이터 저장
				session.setAttribute("loginInfo", memberDTO);
			}
			
			contentPage = "member/login_result.jsp";
		}
		
		//로그아웃 기능
		if(command.equals("/logout.me")) {
			//1. 세션 기능을 사용하기 위해 세션 객체 생성
			//후 세션에 저장된 정보 가져오기
			HttpSession session = request.getSession();
			
			//세션에 저장된 데이터 삭제
			session.removeAttribute("loginInfo");
			
			//페이지 이동
			isRedirect = true;
			page = "itemList.it";
		}
		
		
		//컨텐트 페이지로 이동
		request.setAttribute("contentPage", contentPage);
		
		//페이지 이동
		if(isRedirect) {
			response.sendRedirect(page);
		}
		
		else if(!isRedirect){
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);	
		}
	}
}

