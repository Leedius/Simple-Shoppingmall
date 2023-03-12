package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.CartDTO;
import dto.CartViewDTO;
import sqlmap.SqlSessionManager;

public class CartServiceImpl implements CartService {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//sqlSession 객체로 모든 디비 작업실행.
	SqlSession sqlSession = sqlSessionFactory.openSession();

	//카트 목록 조회
	@Override
	public List<CartDTO> selectCartList(CartViewDTO cartViewDTO) {
		List<CartDTO> cartList = sqlSession.selectList("cartMapper.selectCartList", cartViewDTO);
		sqlSession.commit();
		return cartList;
	}

	//카트 등록
	@Override
	public int regCart(CartDTO cartDTO) {
		int result = sqlSession.insert("cartMapper.regCart", cartDTO);
		sqlSession.commit();
		return result;
	}

	//카트 품목 수량 수정
	@Override
	public int updateCartItemQuantity(CartDTO cartDTO) {
		int result = sqlSession.update("cartMapper.updateCartItemQuantity", cartDTO);
		sqlSession.commit();
		return result;
	}

	//카트 품목 삭제
	@Override
	public int deleteCartItem(String cateCode) {
		int result = sqlSession.delete("cartMapper.deleteCartItem", cateCode);
		sqlSession.commit();
		return result;
	}

	//카트 선택 품목 삭제
	@Override
	public int selectDelete(CartDTO cartDTO) {
		int result = sqlSession.delete("cartMapper.selectDeleteCart",cartDTO);
		sqlSession.commit();
		return result;
	}

	//카트 선택 품목 구매
	@Override
	public CartViewDTO selectCartForBuy(String cartCode) {
		CartViewDTO cartViewDTO = sqlSession.selectOne("cartMapper.selectCartForBuy", cartCode);
		sqlSession.commit();
		return cartViewDTO;
	}
	
}
