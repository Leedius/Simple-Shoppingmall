package service;

import dto.BuyDTO;
import dto.BuyDetailDTO;
import dto.CartDTO;

public interface BuyService {
	//다음에 들어갈 buy_code조회
	public String selectNextBuyCode();
	
	//구매 정보 등록
	public void regBuy(BuyDTO buyDTO, BuyDetailDTO buyDetailDTO, CartDTO cartDTO);
	
	//구매 상세 정보 등록
	public void regBuyDetails(BuyDetailDTO buyDetailDTO);
}
