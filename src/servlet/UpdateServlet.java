package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.TodoListLogic;
import model.TodoListModel;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 何も処置を行わず、メイン画面にリダイレクト
		response.sendRedirect("./Main");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		int isCompleted = Integer.parseInt(request.getParameter("isCompleted"));
		int isDeleted = 1;
		if (request.getParameter("isDeleted") == null) {
			// isDeletedがnull、つまり、チェックボックスにチェックが入っていなかったら
			isDeleted = 0;
		}
		int id = Integer.parseInt(request.getParameter("id"));

		// 取得したパラメータをレコードに保存
		TodoListModel model = new TodoListModel();
		model.setId(id);
		model.setIsCompleted(isCompleted);
		model.setIsDeleted(isDeleted);
		TodoListLogic logic = new TodoListLogic();
		logic.update(model);

		// メイン画面にリダイレクト
		response.sendRedirect("./Main");
	}
}
