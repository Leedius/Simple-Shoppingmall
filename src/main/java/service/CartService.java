package service;

import java.util.List;

import dto.CartDTO;
import dto.CartViewDTO;

public interface CartService {
	
	//카트 목록 조회
	public List<CartDTO> selectCartList(CartViewDTO cartViewDTO);
	
	//카트 등록
	public int regCart(CartDTO cartDTO);
	
	//카트 품목 수량 수정
	public int updateCartItemQuantity(CartDTO cartDTO);
	
	//카트 품목 삭제
	public int deleteCartItem(String cateCode);
	
	//카트 선택 품목 삭제
	public int selectDelete(CartDTO cartDTO);
	
	//선택 품목 구매
	public CartViewDTO selectCartForBuy(String cartCode);
}
