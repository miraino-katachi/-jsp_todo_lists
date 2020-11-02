package logic;

import java.util.List;

import dao.TodoListDAO;
import model.TodoListModel;

/**
 * TODOリストロジッククラス
 * @author tadashi
 *
 */
public class TodoListLogic {

	/**
	 * TODOリストを全件取得します
	 * @return
	 */
	public List<TodoListModel> getTodoList() {
		TodoListDAO dao = new TodoListDAO();
		List<TodoListModel> list = dao.findAll();
		return list;
	}

	/**
	 * TODOリストを追加します
	 * @param todoListModel
	 * @return
	 */
	public boolean create(TodoListModel model) {
		TodoListDAO dao = new TodoListDAO();
		return dao.create(model);
	}

	/**
	 * TODOリストを更新します
	 * @param model
	 * @return
	 */
	public boolean update(TodoListModel model) {
		TodoListDAO dao = new TodoListDAO();
		return dao.update(model);
	}
}
