package servlet.managerServlet;

import java.io.IOException;
import java.util.List;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

@WebServlet("/userManagement")
public class UserManagementServlet extends HttpServlet {
	private final UserService userService = UserService.getInstance();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			//すべての一般ユーザーを取得し、ユーザー管理画面へ
			List<User> userList = userService.getAllUsers()
					.stream()
					.filter(u -> !u.isAdmin())
					.toList(); //toList()はJava16以降は使える
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/managerJSP/user_management_list.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			System.out.println(e + "\n" + e.getMessage());
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getParameter("action");
			switch (action) {
			case "update":
				userUpdate(request,response);
				break;
			case "register":
				userRegister(request, response);
			case "delete":
				deleteUser(request, response);
				break;
			default:
				break;
			}
		} catch (IOException | ServletException e) {

		}
	}
	
	private void userUpdate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//ユーザー変更画面から情報を受け取り、Userインスタンス生成
		User user = new User(
				request.getParameter("userId"),
				request.getParameter("userName"),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("gender"),
				request.getParameter("password"),
				Boolean.parseBoolean(request.getParameter("isAdmin"))
				);
		//ユーザー情報の変更を実行
		if(userService.updateAllProperty(user) == 0) {
			request.setAttribute("message","変更が失敗しました!");
		}else {
			request.setAttribute("message","変更が成功しました!");
		}
		request.setAttribute("userId", user.getUserId());
		request.getRequestDispatcher("/managerJSP/user_update.jsp").forward(request, response);
	}
	
	private void userRegister(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//ユーザー新規登録画面から情報を受け取り、Userインスタンス生成
		User user = new User(
				request.getParameter("userId"),
				request.getParameter("userName"),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("gender"),
				request.getParameter("password"),
				Boolean.parseBoolean(request.getParameter("isAdmin"))
				);
		//ユーザー登録を実行
		if(userService.addUser(user) == 0) {
			request.setAttribute("message","登録が失敗しました!");
		}else {
			request.setAttribute("message","登録が成功しました!");
		}
		request.getRequestDispatcher("/managerJSP/user_register.jsp").forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
				String userId = request.getParameter("userId");
				if(userService.deleteUserById(userId) == 0){
					request.setAttribute("message","削除が失敗しました!");
				}else {
					request.setAttribute("message","削除が成功しました!");
				}
				doGet(request, response);
			}
	
}
