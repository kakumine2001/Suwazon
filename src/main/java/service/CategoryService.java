package service;

import java.util.List;

import dao.CategoryDAO;
import entity.Category;
//@Service
public final class CategoryService {
    private static final CategoryService instance = new CategoryService();
    private CategoryService() {}
    public static CategoryService getInstance() {
        return instance;
    }

    private final CategoryDAO dao = CategoryDAO.getInstance();

    // カテゴリを追加する
    public int addCategory(Category c) {
        return dao.exeInsert(c);
    }

    // IDでカテゴリを取得する
    public Category getCategoryById(int id) {
        return dao.exeSelectById(id);
    }

    // 全カテゴリを取得する
    public List<Category> getAllCategories() {
        return dao.exeSelectAll();
    }

    // カテゴリ名を更新する
    public int updateCategoryName(int id, String name) {
        return dao.exeUpdateName(id, name);
    }

    // カテゴリを削除する
    public int deleteCategoryById(int id) {
        return dao.exeDelete(id);
    }
}
