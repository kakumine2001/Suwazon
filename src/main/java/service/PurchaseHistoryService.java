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
}
