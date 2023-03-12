package service;

import java.util.List;

import dto.ImgDTO;
import dto.ItemCategoryDTO;
import dto.ItemDTO;

public interface ItemService {
	//아이템 카테고리 조회
	List<ItemCategoryDTO> selectItemCategory();
	
	//특정 카테고리 네임 조회
	ItemCategoryDTO selectCategoryName(String cateCode);
	
	//아이템 목록 조회
	List<ItemDTO> selectItemList();
	
	//특정 아이템 목록 조회
	List<ItemDTO> selectCateList(String cateCode);
	
	//아이템 상세 조회
	public ItemDTO selectItemDetail(String itemCode);
	
	//아이템 상세 조회시 이미지 가져오기
	public List<ImgDTO> selectImgList(String itemCode);
}
