package servlet;

import java.io.IOException;
import java.util.List;

import entity.PurchaseHistory;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PurchaseHistoryService;
@WebServlet("/purchaseHistory")
public class PurchaseHistoryServlet extends HttpServlet{
	private static final PurchaseHistoryService phService = PurchaseHistoryService.getInstance();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションからuserIdを取得して、そのユーザーの購入履歴を取得
		HttpSession session = request.getSession();
		String user_id = ((User)session.getAttribute("user")).getUser_id();
		List<PurchaseHistory> phs = phService.getAllHistoriesByUserId(user_id);
		
		//日付ごとに購入履歴を分ける
		
		request.setAttribute("purchaseHistorys", phs);
		request.getRequestDispatcher("/purchase_history.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
	}
}
