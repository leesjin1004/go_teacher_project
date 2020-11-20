package service;

import java.util.List;

import dao.UserDAO_Mariadb;
import vo.UserVo;
public class UserServiceImpl implements UserService{
	
	private UserDAO_Mariadb dao = null;
	
	public UserServiceImpl() {
		super();
	}
	public UserServiceImpl(UserDAO_Mariadb dao) {
		super();
		this.dao = dao;
	}
	public UserDAO_Mariadb getDao() {
		return dao;
	}
	public void setDao(UserDAO_Mariadb dao) {
		this.dao = dao;
	}
	@Override
	public UserVo login(String id, String password) {
		return dao.login(id, password);
	}
	@Override
	public UserVo login(UserVo vo) {
		return dao.login(vo);
	}
	@Override
	public List<UserVo> userList() {
		// TODO Auto-generated method stub
		return null;
	}
}
