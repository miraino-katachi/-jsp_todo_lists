package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.TodoListLogic;
import model.TodoListModel;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODOリストを取得してリクエストスコープに保存する
		TodoListLogic logic = new TodoListLogic();
		List<TodoListModel> list = logic.getTodoList();
		request.setAttribute("todoList", list);

		// 今日の日付を取得してリクエストスコープに保存する
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date);
		request.setAttribute("today", today);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		java.sql.Date expirationDate = java.sql.Date.valueOf(request.getParameter("expirationDate"));
		String todoItem = request.getParameter("todoItem");

		// 取得したパラメータをレコードに保存
		TodoListModel model = new TodoListModel();
		model.setExpirationDate(expirationDate);
		model.setTodoItem(todoItem);
		TodoListLogic logic = new TodoListLogic();
		logic.create(model);

		// メイン画面にリダイレクト
		response.sendRedirect("./Main");
	}
}
