package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * TODOリストフィルタ
 * @author tadashi
 *
 */
@WebFilter("/*")
public class TodoListFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		// このあとdoFilter()メソッドを実行すると「レスポンスをコミットした後でフォワードできません」という例外が発生する。
		// ServletRequestをHttpServletRequestにキャストする必要がある
// 		((HttpServletResponse) response).sendRedirect("https://minnano.miraino-katachi.com/staff/");

		chain.doFilter(request, response);

	}

	public void destroy() {

	}
}
