/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.79
 * Generated at: 2023-08-29 00:23:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class firstView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("        <style>\r\n");
      out.write("        .bd-placeholder-img {\r\n");
      out.write("            font-size: 1.125rem;\r\n");
      out.write("            text-anchor: middle;\r\n");
      out.write("            -webkit-user-select: none;\r\n");
      out.write("            -moz-user-select: none;\r\n");
      out.write("            user-select: none;\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        @media (min-width: 768px) {\r\n");
      out.write("            .bd-placeholder-img-lg {\r\n");
      out.write("            font-size: 3.5rem;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("        </style>\r\n");
      out.write("        \r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            function changeFirstView(value) {\r\n");
      out.write("                if(value == 0) \r\n");
      out.write("                {\r\n");
      out.write("                    location.href = \"SignUpForm.do\";\r\n");
      out.write("                } \r\n");
      out.write("                else if(value == 1)\r\n");
      out.write("                {\r\n");
      out.write("                    location.href = \"LoginForm.do\";\r\n");
      out.write("                } else if(value == 2) \r\n");
      out.write("                {\r\n");
      out.write("                    location.href = \"BoardListForm.bo\"; //아직 미구현\r\n");
      out.write("                } else if(value == 3) \r\n");
      out.write("                {\r\n");
      out.write("                    const link = \"https://github.com/tnaks036/smtv_board\";\r\n");
      out.write("                    window.open(link);\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        </script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h1 class=\"visually-hidden\">요긴 로그인 쓸모는 없습니다</h1>\r\n");
      out.write("        <!-- 세션에 id 값이 있으면?(로그인이 되어 있으면) -->  \r\n");
      out.write("        <!-- <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.memberID!=null}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"> -->\r\n");
      out.write("            <div class=\"px-4 py-5 my-5 text-center\">\r\n");
      out.write("                <img class=\"d-block mx-auto mb-4\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/docs/5.1/assets/brand/bootstrap-logo.svg\" alt=\"\" width=\"72\" height=\"57\">\r\n");
      out.write("                <h1 class=\"display-5 fw-bold\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.memberID}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" 환영합니다!</h1>\r\n");
      out.write("                <div class=\"col-lg-6 mx-auto\">\r\n");
      out.write("                    <p class=\"lead mb-4\">게시판에 글을 남기고 댓글을 남겨주세요!\r\n");
      out.write("                        <br> 해당 게시판에 관한 내용은 GitHub를 통해 확인할 수 있습니다.\r\n");
      out.write("                    </p>\r\n");
      out.write("                    <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\r\n");
      out.write("                        <button type=\"button\" class=\"btn btn-primary btn-lg px-4 gap-3\" onclick=\"changeFirstView(2)\">게시판 바로가기</button>\r\n");
      out.write("                        <button type=\"button\" class=\"btn btn-outline-secondary btn-lg px-4\" onclick=\"changeFirstView(3)\">깃허브 바로가기</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        <!-- </c:if>  -->  \r\n");
      out.write("        \r\n");
      out.write("        <!-- 세션에 id 값이 널이면?(로그인이 안되어 있으면) -->\r\n");
      out.write("        <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.memberID==null}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <div class=\"px-4 py-5 my-5 text-center\">\r\n");
      out.write("                <img class=\"d-block mx-auto mb-4\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/docs/5.1/assets/brand/bootstrap-logo.svg\" alt=\"\" width=\"72\" height=\"57\">\r\n");
      out.write("                <h1 class=\"display-5 fw-bold\">방문을 환영합니다!</h1>\r\n");
      out.write("                <div class=\"col-lg-6 mx-auto\">\r\n");
      out.write("                    <p class=\"lead mb-4\">게시판에 글을 남기고 댓글을 남겨주세요!\r\n");
      out.write("                        <br> 게시판에 글을 남기기 위해서는 회원가입과 로그인이 필요합니다.\r\n");
      out.write("                    </p>\r\n");
      out.write("                    <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\r\n");
      out.write("                        <button type=\"button\" class=\"btn btn-primary btn-lg px-4 gap-3\" onclick=\"changeFirstView(0)\">회원가입 하러가기</button>\r\n");
      out.write("                        <button type=\"button\" class=\"btn btn-outline-secondary btn-lg px-4\" onclick=\"changeFirstView(1)\">로그인 하러가기</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </c:if>  \r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
