package service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BuyDTO;
import dto.BuyDetailDTO;
import dto.CartDTO;
import sqlmap.SqlSessionManager;

public class BuyServiceImpl implements BuyService {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//sqlSession 객체로 모든 디비 작업실행.
	SqlSession sqlSession = sqlSessionFactory.openSession();

	//다음에 들어갈 buy_code조회
	@Override
	public String selectNextBuyCode() {
		String nextBuyCode = sqlSession.selectOne("buyMapper.selectNextBuyCode");
		sqlSession.commit();
		return nextBuyCode;
	}

	//구매 정보 등록
	@Override
	public void regBuy(BuyDTO buyDTO, BuyDetailDTO buyDetailDTO, CartDTO cartDTO) {
		try {
			sqlSession.insert("buyMapper.regBuy", buyDTO);
			sqlSession.insert("buyMapper.regBuyDetails", buyDetailDTO);
			sqlSession.delete("cartMapper.selectDeleteCart",cartDTO);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		
	}

	//구매 상세 정보 등록
	@Override
	public void regBuyDetails(BuyDetailDTO buyDetailDTO) {
		sqlSession.insert("buyMapper.regBuyDetails", buyDetailDTO);
		sqlSession.commit();
	}

}
