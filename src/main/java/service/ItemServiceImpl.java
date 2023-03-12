package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.ImgDTO;
import dto.ItemCategoryDTO;
import dto.ItemDTO;
import sqlmap.SqlSessionManager;

public class ItemServiceImpl implements ItemService {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//sqlSession 객체로 모든 디비 작업실행.
	SqlSession sqlSession = sqlSessionFactory.openSession();

	//카테고리 조회
	@Override
	public List<ItemCategoryDTO> selectItemCategory() {
		List<ItemCategoryDTO> itCategoryDTO = sqlSession.selectList("itemMapper.selectCategory");
		sqlSession.commit();
		return itCategoryDTO;
	}

	//아이템 목록 조회
	@Override
	public List<ItemDTO> selectItemList() {
		List<ItemDTO> itemList = sqlSession.selectList("itemMapper.selectItemList");
		sqlSession.commit();
		return itemList;
	}
	
	//특정 아이템 목록 조회
	@Override
	public List<ItemDTO> selectCateList(String cateCode) {
		List<ItemDTO> itemCateList = sqlSession.selectList("itemMapper.selectCateList", cateCode);
		sqlSession.commit();
		return itemCateList;
	}

	//아이템 상세 조회
	@Override
	public ItemDTO selectItemDetail(String itemCode) {
		ItemDTO itemDetail = sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
		sqlSession.commit();
		return itemDetail;
	}

	//특정 카테고리 이름 조회
	@Override
	public ItemCategoryDTO selectCategoryName(String cateCode) {
		ItemCategoryDTO categoryName = sqlSession.selectOne("itemMapper.selectCategoryName", cateCode);
		sqlSession.commit();
		return categoryName;
	}

	//아이템 상세 조회시 이미지 가져오기
	@Override
	public List<ImgDTO> selectImgList(String itemCode) {
		List<ImgDTO> imgList = sqlSession.selectList("itemMapper.selectImgList", itemCode);
		sqlSession.commit();
		return imgList;
	}



}
