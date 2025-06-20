package service;

import java.util.List;

import dao.PurchaseHistoryDAO;
import entity.PurchaseHistory;

public class PurchaseHistoryService {
    private static final PurchaseHistoryService instance = new PurchaseHistoryService();
    private PurchaseHistoryService() {}
    public static PurchaseHistoryService getInstance() {
        return instance;
    }

    private static final PurchaseHistoryDAO dao = PurchaseHistoryDAO.getInstance();

    // 全購入履歴を取得する
    public List<PurchaseHistory> getAllHistories() {
        return dao.exeSelectAll();
    }
    
    //指定したユーザーの履歴を取得
    public List<PurchaseHistory> getAllHistoriesByUserId(String user_id) {
        return dao.exeSelectByUserid(user_id);
    }
    
    //購入履歴を登録
    public int purchaseComplete(int product_id,String user_id,int quantity) {
    	return dao.exeInsert(product_id, user_id, quantity);
    }
    

}
