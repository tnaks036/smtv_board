package jsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class EncodingFilter
 	annotation 사용 가능, 전체 서블릿에 동작하기 /*
 */
@WebFilter("/*") 
public class EncodingFilter implements Filter {
	//private static final long serialVersionUID = 1L;
      
	//필터 초기화
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		
	}

//    public EncodingFilter() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

  //필터가 요청시 필터가 실행할 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);			//체인의 다음 필터 처리
        
        //1. 서블릿에서 특정 요청에 대해 데이터를 처리한다.
        //2. view 페이지에 데이터가 전달되기 전에 필터가 동작하여 한글이 깨지지 않게 인코딩한다.
        //3. chain.doFilter 로 View 페이지로 전환한다.
    }
}
