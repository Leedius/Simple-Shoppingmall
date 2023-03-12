package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.tomcat.util.net.ApplicationBufferHandler;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.ImgDTO;
import dto.ItemCategoryDTO;
import dto.ItemDTO;
import dto.MemberDTO;
import service.AdminService;
import service.AdminServiceImpl;
import service.ItemService;
import service.ItemServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("*.ad")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//멤버 서비스를 사용할 수 있게 만드는 객체 생성
	//자료형을 인터페이스로 만들도록 한다.
	private ItemService itemService = new ItemServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
    public AdminController() {
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
		
		//상품 등록 페이지로 이동
		if(command.equals("/regItemForm.ad")) {
			//아이템 카테고리를 담을 객체 생성
			List<ItemCategoryDTO> itemCategory = null;
			//아이템 카테고리를 가져오는 쿼리 실행
			itemCategory = itemService.selectItemCategory();
			request.setAttribute("itemCategory", itemCategory);
			
			contentPage = "admin/item_reg_form.jsp";
		}
		
		//상품 등록
		if(command.equals("/regItem.ad")) {
			//파일 첨부
			//1. 파일이 저장될 위치
			String uploadPath = "C:\\Users\\tiuyt\\OneDrive\\바탕 화면\\dev\\workspce_eclips\\Shop\\src\\main\\webapp\\images";
			
			//2. 업로드 용량 제한
			int uploadSize = 10 * 1024 * 1024; //10메가
			
			//3. 파일 첨부 실행(multi에 첨부된 파일정보를 저장한다)
			MultipartRequest multi = new MultipartRequest(request
															, uploadPath
															, uploadSize
															, "UTF-8"
															, new DefaultFileRenamePolicy() //파일명이 중복일때 뒤에 숫자를 붙여주는 클래스
															);
			
			//상품 데이터 받기
			//첨부파일 데이터가 같이 넘어온 경우에는
			//request로 데이터를 받는게 아니라 multi 객체로 데이터를 받아야 함.
			String cateCode = multi.getParameter("cateCode");
			String itemName = multi.getParameter("itemName");
			int itemPrice = Integer.parseInt(multi.getParameter("itemPrice")); 
			String itemIntro = multi.getParameter("itemIntro");
			
			//다음에 들어갈 ITEM_CODE를 조회
			String nextItemCode = adminService.selectNextItemCode();
			
			//상품 데이터 저장할 객체 생성
			ItemDTO itemDTO = new ItemDTO();
			
			//객체에 데이터 저장
			itemDTO.setItemCode(nextItemCode);
			itemDTO.setCateCode(cateCode);
			itemDTO.setItemName(itemName);
			itemDTO.setItemPrice(itemPrice);
			itemDTO.setItemIntro(itemIntro);
			
			//상품 기본 정보 등록 쿼리 실행
			adminService.regItem(itemDTO);
			
			//상품 이미지 정보 등록(ITEM_IMG)
			//getFileNames() -> 첨부파일이 있는
			//input태그의 name속성값을 가져 옴.
			Enumeration<String> inputTagNames = multi.getFileNames();	//Enumeration<String> 문자열이 여러개 들어가있음
			//mainImg, subImg
			
			//쿼리의 모든 데이터를 채울 수 있는 객체 생성
			List<ImgDTO> imgList = new ArrayList<>();
			
			//inputTagNames에 데이터가 있는 동안 반복
			while(inputTagNames.hasMoreElements()) {
				//하나의 이미지 정보를 다 담을 수 있는 객체 생성
				ImgDTO img = new ImgDTO();
				
				//file이 들어간 인풋태그의 name 속성을 하나씩 가져 옴.
				String fileInputTag = inputTagNames.nextElement();
				
				//name 속성이 mainTag인 태그의 원본 첨부파일명 불러 옴.
				String originFileName = multi.getOriginalFileName(fileInputTag);
				String attachedFileName = multi.getFilesystemName(fileInputTag);
			
				//하나의 이미지 정보를 생성한 객체에 저장
				img.setOriginFileName(originFileName);
				img.setAttachedFileName(attachedFileName);
				img.setItemCode(nextItemCode);
				
				//isMain값 저장
				if(fileInputTag.equals("mainImg")) {
					img.setIsMain("Y");
				}
				else {
					img.setIsMain("N");
				}
				
				//완성된 하나의 이미지 정보를 list에 추가
				imgList.add(img);
			}
			
			//쿼리 실행 시 빈 값을 채워줄 객체
			ImgDTO imgDTO = new ImgDTO();
			imgDTO.setImgList(imgList);
			
			//ITEM_IMG 테이블에 INSERT
			adminService.regImg(imgDTO);
			
			isRedirect = true;
			page = "itemList.it";
		}
		
		//아이템 정보 수정
		if(command.equals("/goUpdateItem.ad")) {
			String itemCode = request.getParameter("itemCode");
			
			//아이템 정보를 담을 객체 생성
			ItemDTO itemDetail = new ItemDTO();
			List<ItemCategoryDTO> itemCategoryList = null;
			
			//아이템 상세정보를 객체에 저장
			itemDetail = itemService.selectItemDetail(itemCode);
			
			//카테고리 네임을 받아오기 위해 카테고리 조회 쿼리 실행
			itemCategoryList = itemService.selectItemCategory();
			
			//아이템 정보를 수정 form으로 전달
			request.setAttribute("itemDetail", itemDetail);
			request.setAttribute("itemCategory", itemCategoryList);
			
			contentPage = "admin/item_update_form.jsp";
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

