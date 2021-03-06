package com.projecthh.company.user.service;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.projecthh.company.notice.vo.NoticeVO;
import com.projecthh.company.qna.vo.QnaVO;
import com.projecthh.company.user.DAO.UserDAO;
import com.projecthh.company.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Override
	public UserVO login(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.login(vo);
	}

	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}

	@Override
	public void updateUserState(Map<String, Object> map) {
		userDAO.updateUserState(map);
	}

	@Override
	public void updateAuthKey(Map<String, Object> map) {
		userDAO.updateAuthKey(map);
	}

	@Override
	public int selectUser(Map<String, Object> map) {
		return userDAO.selectUser(map);
	}

	@Override
	public UserVO userInfo(String no) throws Exception {

		return userDAO.userInfo(no);
	}

	@Override
	public void infoupdate(UserVO dto) {

		userDAO.infoupdate(dto);
	}

	@Override
	public int checkID(String id) {
		return userDAO.checkID(id);
	}

	@Override
	public ArrayList<NoticeVO> notice() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.notice();
	}

	@Override
	public ArrayList<QnaVO> qna() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.qna();
	}
}