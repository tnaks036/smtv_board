package jsp.common.action;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public interface Action { //'~~Action'으로 들어오는 모든 요청은 Action인터페이스를 구현해야함.(polymorphism)
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
    // An implemented Action class is defined to take a request and response object via the execute() method 
    //and return an ActionForward object with path and redirect information.

}
