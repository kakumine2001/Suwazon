package servlet;

import java.io.IOException;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
	private static final UserService userService = UserService.getInstance();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage() + "\n" + e);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			switch (action) {
			case "login":
				login(request, response, session);
				break;
			case "registration":
				registration(request, response);
				break;
			default:
				logout(request, response, session);
				break;
			}

		} catch (IOException | ServletException e) {
			System.out.println(e + "\n" + e.getMessage());
		}
	}

	//ユーザーログイン処理
	private void login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	        throws ServletException, IOException {
	    String userId = request.getParameter("user_id");
	    String password = request.getParameter("password");

	    if (!userService.isExitingUser(userId, password)) {
	        request.setAttribute("message", "パスワードまたはユーザーIDが間違っています");
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	    } else {
	        User loginUser = userService.getUserById(userId);
	        session.setAttribute("user", loginUser);
	        if (loginUser.isAdmin()) {
	            // 管理者なら管理者ホームへ
	            request.getRequestDispatcher("/managerJSP/admin_home.jsp").forward(request, response);
	        } else {
	            // 一般ユーザーなら商品一覧へ
	            request.getRequestDispatcher("/product_list").forward(request, response);
	        }
	    }
	}


	//ユーザー新規登録処理
	private void registration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		User newUser = new User(userId, userName, age, gender, password, false);
		if (userService.userRegistration(newUser) == 0) {
			request.setAttribute("message", "ユーザーがすでに存在いているか、入力が間違っています。");
		} else {
			request.setAttribute("message", "登録が完了しました");
		}
		request.getRequestDispatcher("/registration.jsp").forward(request, response);
	}
	
	//ログアウト処理
	private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
			throws ServletException, IOException{
		session.invalidate();
		request.setAttribute("message","ログアウトしました");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
