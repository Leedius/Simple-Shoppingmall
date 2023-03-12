package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.ImgDTO;
import dto.ItemDTO;
import sqlmap.SqlSessionManager;

public class AdminServiceImpl implements AdminService {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//sqlSession 객체로 모든 디비 작업실행.
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	//아이템 코드 조회
	@Override
	public String selectNextItemCode() {
		String nextItemCode = sqlSession.selectOne("adminMapper.selectNextItemCode");
		sqlSession.commit();
		return nextItemCode;
	}

	//아이템 등록
	@Override
	public int regItem(ItemDTO itemDTO) {
		int resultReg = sqlSession.insert("adminMapper.regItem", itemDTO); 
		sqlSession.commit();
		return resultReg;
	}

	//이미지 등록
	@Override
	public int regImg(ImgDTO imgDTO) {
		int result = sqlSession.insert("adminMapper.regImg", imgDTO);
		sqlSession.commit();
		return result;
	}

}
