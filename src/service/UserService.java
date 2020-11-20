package service;

import java.util.List;
import vo.UserVo;

public interface UserService {
	public UserVo login(UserVo vo);
	public UserVo login(String id, String password);

	public List<UserVo> userList();
}
