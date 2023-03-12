package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BuyDTO;
import dto.BuyDetailDTO;
import dto.CartDTO;
import dto.CartViewDTO;
import dto.MemberDTO;
import service.BuyService;
import service.BuyServiceImpl;
import service.CartService;
import service.CartServiceImpl;


@WebServlet("*.bu")
public class BuyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	private BuyService buyService = new BuyServiceImpl();
	
    public BuyController() {
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
		
		//선택 구매
		if(command.equals("/selectRegBuy.bu")) {
			//다음에 들어갈 구매 코드 조회
			String nextBuyCode = buyService.selectNextBuyCode();
			
			//"CART_001,CART_002....."
			String cartCodes = request.getParameter("cartCodes");
			String[] cartCodesArr = cartCodes.split(",");
			List<String> cartCodesList = Arrays.asList(cartCodesArr);
			
			//세션정보 받아오기
			HttpSession session = request.getSession();
			MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginInfo");
			String memId = loginInfo.getMemId();
			
			//1. 총가격을 저장할 변수 생성
			int buyPrice = 0;
			//2.하나하나의 상품의 정보들을 담을 객체생성
			List<BuyDetailDTO> buyDetailList = new ArrayList<>();
			//3.선택한 카트 코드가 있는만큼 반복
			for(String cartCode : cartCodesList) {
				//구매하고자 하는 상품의 정보를 조회
				CartViewDTO cartViewDTO = cartService.selectCartForBuy(cartCode);
				//선택한 카트리스트의 품목의 총가격을 순서대로 더해서 
				//선택구매할 품목의 총가격
				buyPrice += cartViewDTO.getTotalPrice();
				
				//구매하고자 하는 선택 품목중에서 하나하나의 상세정보를 담을 객체 생성후
				//각각의 상품에대한 정보를 저장
				BuyDetailDTO buyDetailDTO = new BuyDetailDTO();
				buyDetailDTO.setItemCode(cartViewDTO.getItemCode());
				buyDetailDTO.setBuyQuantity(cartViewDTO.getItemQuantity());
				buyDetailDTO.setBuyPrice(cartViewDTO.getTotalPrice());
				buyDetailDTO.setBuyCode(nextBuyCode);
				
				//반복할때마다 하나의 상품정보를 리스트에 저장
				buyDetailList.add(buyDetailDTO);
				
		
			}
			
			//구매 상품 정보 등록
			BuyDTO buyDTO = new BuyDTO();
			buyDTO.setBuyCode(nextBuyCode);
			buyDTO.setMemId(memId);
			buyDTO.setBuyPrice(buyPrice);
			
			//구매 상품 상세 정보 등록
			BuyDetailDTO buyDetailDTO = new BuyDetailDTO();
			buyDetailDTO.setBuyDetailList(buyDetailList);
			
			//구매한 상품 장바구니에서 삭제
			CartDTO cartDTO = new CartDTO();
			cartDTO.setCartCodeList(cartCodesList);
			
			//구매등록, 구매 상품 상세 정보, 구매이동후 장바구니 상품 삭제 한번에 실행
			buyService.regBuy(buyDTO, buyDetailDTO, cartDTO);
			
			contentPage = "";
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

