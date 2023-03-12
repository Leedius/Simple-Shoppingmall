package service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sqlmap.SqlSessionManager;

public class BuyDetailServiceImpl implements BuyDetailService {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//sqlSession 객체로 모든 디비 작업실행.
	SqlSession sqlSession = sqlSessionFactory.openSession();
	

}
