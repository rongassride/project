package com.projecthh.company.user.DAO;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.projecthh.common.AbstractDAO;
import com.projecthh.company.notice.vo.NoticeVO;
import com.projecthh.company.qna.vo.QnaVO;
import com.projecthh.company.user.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {

	public UserVO login(UserVO vo) {
		return (UserVO) selectOne("user.login", vo);
	}

	public void insertUser(UserVO vo) {
		insert("user.insertUser", vo);
	}

	public void updateAuthKey(Map<String, Object> map) {
		update("user.updateAuthKey", map);
	}

	public void updateUserState(Map<String, Object> map) {
		update("user.updateUserState", map);
	}

	public int selectUser(Map<String, Object> map) {
		return (Integer) selectOne("user.selectUser", map);
	}

	public UserVO userInfo(String no) {

		return (UserVO) selectOne("userInfo", no);
	}

	public void infoupdate(UserVO dto) {

		update("infoupdate", dto);
	}

	public int checkID(String id) {
		return (Integer) selectOne("user.checkID", id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<NoticeVO> notice() {
		// TODO Auto-generated method stub
		return (ArrayList<NoticeVO>) selectList("user.notice");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<QnaVO> qna() {
		// TODO Auto-generated method stub
		return (ArrayList<QnaVO>) selectList("user.qna");
	}
}