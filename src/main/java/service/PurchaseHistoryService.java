package service;

import java.time.LocalDate;
import java.util.List;

import dao.PurchaseHistoryDAO;
import entity.PurchaseHistory;
//@Service
public final class PurchaseHistoryService {
    private static final PurchaseHistoryService instance = new PurchaseHistoryService();
    private PurchaseHistoryService() {}
    public static PurchaseHistoryService getInstance() {
        return instance;
    }

    private final PurchaseHistoryDAO dao = PurchaseHistoryDAO.getInstance();

    // 全購入履歴を取得する
    public List<PurchaseHistory> getAllHistories() {
        return dao.exeSelectAll();
    }
    
    //指定したユーザーの履歴を取得
    public List<PurchaseHistory> getAllHistoriesByUserId(String user_id) {
        return dao.exeSelectByUserid(user_id);
    }
    
    //指定したユーザーidと商品idと日付の購入履歴を取得
    public PurchaseHistory getHistoryByUserIdAndProductIdAndDate(String userId,int productId,LocalDate date) {
    	return dao.exeSelectByUserIdAndProductIdAndDate(userId, productId,date);
    }
    
    //購入履歴を登録
    public int purchaseComplete(int product_id,String user_id,LocalDate date,int quantity) {
    	PurchaseHistory ph = getHistoryByUserIdAndProductIdAndDate(user_id, product_id,date);
    	System.out.println(ph);
    	if(ph == null){ //テーブルに同じユーザー、同じ商品、同じ日付のレコーダがあるか検証
    		return dao.exeInsert(product_id, user_id, quantity);
    	}else{   //同じユーザー同じ商品同じ日に購入する場合、数だけ増加する
    		return dao.updatePurchaseHistoryNumber(ph.getNumber() + quantity, user_id, product_id);
    	}
    }
    

}
