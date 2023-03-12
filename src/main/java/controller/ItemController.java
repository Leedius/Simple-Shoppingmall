package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ItemCategoryDTO;
import dto.ItemDTO;
import service.ItemService;
import service.ItemServiceImpl;


@WebServlet("*.it")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//아이템 카테고리 서비스를 사용할 수 있게 만드는 객체 생성
	//자료형을 인터페이스로 만들도록 한다.
	private ItemService itemService = new ItemServiceImpl();
	
    public ItemController() {
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
		
		//상품 목록 페이지로 이동
		if(command.equals("/itemList.it")) {
			List<ItemDTO> itemList = null;
			
			//상품목록 조회
			itemList = itemService.selectItemList();
			
			request.setAttribute("itemList", itemList);
			contentPage = "item/item_list.jsp";
		}
		
		//특정 목록 페이지로 이동
		if(command.equals("/selectItemList.it")) {
			//카테코드 데이터받기
			String cateCode = request.getParameter("cateCode");
			
			//특정 목록 담을 페이지 객체 생성
			List<ItemDTO> selectItemList = null;
			
			//특정 카테고리 상품 목록 조회
			selectItemList = itemService.selectCateList(cateCode);
			
			request.setAttribute("itemList", selectItemList);
			contentPage = "item/item_list.jsp";
		}
		
		//상품 상세 정보 조회
		if(command.equals("/goItemDetail.it")) {
			String itemCode = request.getParameter("itemCode");
			
			//상품 상세 정보 담을 객체 생성
			ItemDTO itemDetail = new ItemDTO();
			ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
			
			//상세정보 쿼리 실행하여 객체에 저장
			itemDetail = itemService.selectItemDetail(itemCode);
			
			System.out.println(itemDetail);
			
			//카테고리 네임을 받아오기 위해 카테고리 조회 쿼리 실행
			itemCategoryDTO = itemService.selectCategoryName(itemDetail.getCateCode());
			
			//데이터 전송
			request.setAttribute("itemDetail", itemDetail);
			request.setAttribute("itemCategory", itemCategoryDTO);
			
			contentPage = "item/item_detail.jsp";
		}
		
		//장바구니 페이지로 이동
		if(command.equals("/cartList.it")) {
			
			contentPage = "item/cart_list.jsp";
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

