package service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.MemberDTO;
import sqlmap.SqlSessionManager;

public class MemberServiceImpl implements MemberService {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//sqlSession 객체로 모든 디비 작업실행.
	SqlSession sqlSession = sqlSessionFactory.openSession();

	//회원가입
	@Override
	public int joinMember(MemberDTO memberDTO) {
		int addMember = sqlSession.insert("memberMapper.joinMember", memberDTO);
		sqlSession.commit();
		return addMember;
	}

	//아이디 중복 확인
	@Override
	public boolean idCheck(String memId) {
		MemberDTO idCheck = sqlSession.selectOne("memberMapper.idCheck", memId);
		sqlSession.commit();
		return idCheck != null ? true : false;
	}

	//로그인 기능
	@Override
	public MemberDTO login(MemberDTO memberDTO) {
		MemberDTO loginMember = sqlSession.selectOne("memberMapper.login", memberDTO);
		sqlSession.commit();
		return loginMember;
	}

}
