package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dto.CartDTO;
import dto.CartViewDTO;
import dto.ItemDTO;
import dto.MemberDTO;
import service.CartService;
import service.CartServiceImpl;
import service.ItemService;
import service.ItemServiceImpl;


@WebServlet("*.ct")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//멤버 서비스를 사용할 수 있게 만드는 객체 생성
	//자료형을 인터페이스로 만들도록 한다.
	private CartService cartService = new CartServiceImpl();
	private ItemService itemService = new ItemServiceImpl();
	
    public CartController() {
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
		
		//카트 목록 으로 이동
		if(command.equals("/cartList.ct")) {
			//카트 리스트를 담을 객체 생성
			List<CartDTO> cartList = null;
			
			String isMain = "Y";
			//로그인 정보 받아오기
			//1. 세션기능을 사용하기 위해 세션 객체 생성
			HttpSession session = request.getSession();
			
			//2. 세션에 저장된 정보 저장
			MemberDTO member = (MemberDTO)session.getAttribute("loginInfo");
			
			//매개변수값을 담을 객체 생성
			CartViewDTO cartViewDTO = new CartViewDTO();
			
			cartViewDTO.setMemId(member.getMemId());
			cartViewDTO.setIsMain(isMain);
			
			//카트 리스트를 가져오는 쿼리 실행
			cartList = cartService.selectCartList(cartViewDTO);
			
			//페이지로 카트리스트 정보 전송
			request.setAttribute("cartList", cartList);
			
			contentPage = "cart/cart_list.jsp";
		}
		
		//카트 등록
		if(command.equals("/regCart.ct")) {
			//데이터 받아오기
			String itemCode = request.getParameter("itemCode");
			int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
			
			//로그인 정보 받아오기
			//1. 세션기능을 사용하기 위해 세션 객체 생성
			HttpSession session = request.getSession();
			
			//2. 세션에 저장된 정보 저장
			MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginInfo");
			
			//등록할 아이템 정보 받아오기
			ItemDTO item = itemService.selectItemDetail(itemCode);
			
			//카트 객체 생성
			CartDTO cart = new CartDTO();
			
			//객체에 데이터 저장
			cart.setItemCode(itemCode);
			cart.setItemQuantity(itemQuantity);
			cart.setItemDTO(item);
			cart.setMemId(loginInfo.getMemId());
			
			cartService.regCart(cart);
			
			isRedirect = true;
			page = "cartList.ct";
		}
		
		//카트리스트 특정 수량 변경
		if(command.equals("/cartItemQuantityUpdate.ct")) {
			int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
			String cartCode = request.getParameter("cartCode");
			int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
			
			System.out.println(totalPrice);
			System.out.println(itemQuantity);
			
			//수정할 데이터를 저장할 객체 생성
			CartDTO cartDTO = new CartDTO();
			
			//객체에 데이터 저장
			cartDTO.setItemQuantity(itemQuantity);
			cartDTO.setCartCode(cartCode);
			cartDTO.setTotalPrice(totalPrice);
			
			//수량 수정 쿼리 실행
			cartService.updateCartItemQuantity(cartDTO);
			
			isRedirect = true;
			page = "cartList.ct";
		}
		
		//카트 품목 삭제
		if(command.equals("/deleteCartItem.ct")) {
			String cartCode = request.getParameter("cartCode");
			
			//카트 품목 삭제 쿼리 실행
			cartService.deleteCartItem(cartCode);
			
			//다시 카트 리스트로
			isRedirect = true;
			page = "cartList.ct";
			
		}
		
		//카트 선택 품목 삭제
		if(command.equals("/selectdelete.ct")) {
			//데이터 받기
			String cartCodes = request.getParameter("cartCodes");	//CART_001.CART_002,..
			System.out.println("!!!!"+ cartCodes);//확인용
			//중간에 ','자르면서 문자열배열로 저장
			String[] cartCodeArr = cartCodes.split(",");
			
			//리스트 객체를 생성하고 배열을 저장
			List<String> cartCodeList = Arrays.asList(cartCodeArr);
			
			CartDTO cartDTO = new CartDTO();
			cartDTO.setCartCodeList(cartCodeList);
			
			cartService.selectDelete(cartDTO);

			isRedirect = true;
			page="cartList.ct";
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

