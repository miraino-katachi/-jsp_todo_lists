package logic;

import java.util.List;

import dao.TodoItemDAO;
import model.TodoItemModel;

/**
 * TODOリストロジッククラス
 * @author tadashi
 *
 */
public class TodoItemLogic {

	/**
	 * TODOリストを全件取得します
	 * @return
	 */
	public List<TodoItemModel> getTodoList() {
		TodoItemDAO dao = new TodoItemDAO();
		List<TodoItemModel> list = dao.findAll();
		return list;
	}

	/**
	 * TODOリストを追加します
	 * @param todoListModel
	 * @return
	 */
	public boolean create(TodoItemModel model) {
		TodoItemDAO dao = new TodoItemDAO();
		return dao.create(model);
	}

	/**
	 * TODOリストを更新します
	 * @param model
	 * @return
	 */
	public boolean update(TodoItemModel model) {
		TodoItemDAO dao = new TodoItemDAO();
		return dao.update(model);
	}
}
