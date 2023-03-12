package service;

import dto.ImgDTO;
import dto.ItemDTO;

public interface AdminService {
	//다음 ITEM_CODE조회
	public String selectNextItemCode();
	
	//아이템 등록
	public int regItem(ItemDTO itemDTO);
	
	//상품 이미지 등록
	public int regImg(ImgDTO imgDTO);
	
}
