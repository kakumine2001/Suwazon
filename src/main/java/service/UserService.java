package service;

import java.util.List;

import dao.UserDAO;
import entity.User;
//@Service
public final class UserService {
	private static final UserService instance = new UserService();

	private UserService() {
	}

	public static UserService getInstance() {
		return instance;
	}

	private final UserDAO dao = UserDAO.getInstance();

	
	//ログイン認証
	public boolean isExitingUser(String id, String password) {
		User u = getUserById(id);
		return u != null && password.equals(u.getPassword());
	}

	//新規ユーザー登録
	public int userRegistration(User u) {
		//同じユーザーidを持つユーザーがいたら登録させない
		List<User> users = getAllUsers();
		boolean isExitingUserId =  users.stream()
				   .anyMatch(user -> user.getUserId().equals(u.getUserId()));
		if(isExitingUserId) {
			return 0;
		}
		return addUser(u);
	}
	
	//ユーザー新規追加
	public int addUser(User u) {
		return dao.exeInsert(u);
	}

	//ユーザー削除
	public int deleteUserById(String id) {
		return dao.exeDelete(id);
	}

	//ユーザーのすべてのプロパティを更新
	public int updateAllProperty(User u) {
		return dao.exeUpdateAll(u);
	}

	//名前のみ更新
	public int updateName(int id, String name) {
		return dao.exeUpdateName(id, name);
	}

	//パスワードのみ更新
	public int updatePassword(int id, String password) {
		return dao.exeUpdatePassword(id, password);
	}

	//年齢のみ更新
	public int updateAge(int id, int age) {
		return dao.exeUpdateAge(id, age);
	}

	//指定したidのユーザーを取得
	public User getUserById(String user_id) {
		return dao.exeSelect(user_id);
	}

	//すべてのユーザーを取得してＬｉｓｔで返す
	public List<User> getAllUsers() {
		return dao.exeSeleteAllUsers();
	}
}
