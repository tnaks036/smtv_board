/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.79
 * Generated at: 2023-09-04 06:22:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1693808497452L));
    _jspx_dependants.put("file:/C:/apache-tomcat-9.0.79/lib/jstl-1.2.jar", Long.valueOf(1692858145305L));
    _jspx_dependants.put("jar:file:/C:/apache-tomcat-9.0.79/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1693803603039L));
    _jspx_dependants.put("jar:file:/C:/apache-tomcat-9.0.79/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
  }

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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>메인</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/default.css\">\r\n");
      out.write("<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet' type='text/css'>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script> \r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<nav class=\"navbar navbar-expand-custom navbar-mainbg\">\r\n");
      out.write("	<a class=\"navbar-brand navbar-logo\" href=\"index.jsp\">SMT</a>\r\n");
      out.write("	<button class=\"navbar-toggler\" type=\"button\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("		<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"30\" height=\"30\" fill=\"#ffffff\" class=\"bi bi-list\" viewBox=\"0 0 16 16\">\r\n");
      out.write("		  <path fill-rule=\"evenodd\" d=\"M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z\"/>\r\n");
      out.write("		</svg>\r\n");
      out.write("	</button>\r\n");
      out.write("	<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n");
      out.write("	    <ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("	        <div class=\"hori-selector\"><div class=\"left\"></div><div class=\"right\"></div></div>\r\n");
      out.write("	        <li class=\"nav-item \">\r\n");
      out.write("	            <a class=\"nav-link\" href=\"index.jsp\">HOME</a>\r\n");
      out.write("	        </li>\r\n");
      out.write("	        <li class=\"nav-item \">\r\n");
      out.write("	            <a class=\"nav-link\" href=\"boardPage\">문의</a>\r\n");
      out.write("	        </li>\r\n");
      out.write("	        <li class=\"nav-item\">\r\n");
      out.write("	            <a class=\"nav-link\" href=\"javascript:void(0);\">Components</a>\r\n");
      out.write("	        </li>\r\n");
      out.write("	        <li class=\"nav-item\">\r\n");
      out.write("	            <a class=\"nav-link\" href=\"javascript:void(0);\">Calendar</a>\r\n");
      out.write("	        </li>\r\n");
      out.write("	        <li class=\"nav-item\">\r\n");
      out.write("	            <a class=\"nav-link\" href=\"javascript:void(0);\">Charts</a>\r\n");
      out.write("	        </li>\r\n");
      out.write("	        <li class=\"nav-item\">\r\n");
      out.write("	            <a class=\"nav-link\" href=\"javascript:void(0);\">Documents</a>\r\n");
      out.write("	        </li>\r\n");
      out.write("	    </ul>\r\n");
      out.write("	</div>\r\n");
      out.write("</nav>	\r\n");
      out.write("<script src=\"js/board.js\"></script>	\r\n");
      out.write("<script>\r\n");
      out.write("function test(){\r\n");
      out.write("	  var tabsNewAnim = $('#navbarSupportedContent');\r\n");
      out.write("	  var selectorNewAnim = $('#navbarSupportedContent').find('li').length;\r\n");
      out.write("	  var activeItemNewAnim = tabsNewAnim.find('.active');\r\n");
      out.write("	  var activeWidthNewAnimHeight = activeItemNewAnim.innerHeight();\r\n");
      out.write("	  var activeWidthNewAnimWidth = activeItemNewAnim.innerWidth();\r\n");
      out.write("	  var itemPosNewAnimTop = activeItemNewAnim.position();\r\n");
      out.write("	  var itemPosNewAnimLeft = activeItemNewAnim.position();\r\n");
      out.write("	  $(\".hori-selector\").css({\r\n");
      out.write("	    \"top\":itemPosNewAnimTop.top + \"px\", \r\n");
      out.write("	    \"left\":itemPosNewAnimLeft.left + \"px\",\r\n");
      out.write("	    \"height\": activeWidthNewAnimHeight + \"px\",\r\n");
      out.write("	    \"width\": activeWidthNewAnimWidth + \"px\"\r\n");
      out.write("	  });\r\n");
      out.write("	  $(\"#navbarSupportedContent\").on(\"click\",\"li\",function(e){\r\n");
      out.write("	    $('#navbarSupportedContent ul li').removeClass(\"active\");\r\n");
      out.write("	    $(this).addClass('active');\r\n");
      out.write("	    var activeWidthNewAnimHeight = $(this).innerHeight();\r\n");
      out.write("	    var activeWidthNewAnimWidth = $(this).innerWidth();\r\n");
      out.write("	    var itemPosNewAnimTop = $(this).position();\r\n");
      out.write("	    var itemPosNewAnimLeft = $(this).position();\r\n");
      out.write("	    $(\".hori-selector\").css({\r\n");
      out.write("	      \"top\":itemPosNewAnimTop.top + \"px\", \r\n");
      out.write("	      \"left\":itemPosNewAnimLeft.left + \"px\",\r\n");
      out.write("	      \"height\": activeWidthNewAnimHeight + \"px\",\r\n");
      out.write("	      \"width\": activeWidthNewAnimWidth + \"px\"\r\n");
      out.write("	    });\r\n");
      out.write("	  });\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	$(document).ready(function(){\r\n");
      out.write("		var path = window.location.pathname.split(\"/\").pop();\r\n");
      out.write("		  \r\n");
      out.write("		  // Account for home page with empty path\r\n");
      out.write("		  if ( path == '' ) {\r\n");
      out.write("		    path = 'index.jsp';\r\n");
      out.write("		  }\r\n");
      out.write("		\r\n");
      out.write("		  if(path.toUpperCase().includes(\"BOARD\")){\r\n");
      out.write("			  // navbar 아닌 다른 페이지 이동 시 (상세보기, 글쓰기 등)에도 선택 유지\r\n");
      out.write("	  		path = 'boardPage';		\r\n");
      out.write("  		}\r\n");
      out.write("		  \r\n");
      out.write("	  var target = $('#navbarSupportedContent ul li a[href=\"'+path+'\"]');\r\n");
      out.write("	   if (target.length > 0) {\r\n");
      out.write("	    target.parent().addClass('active');\r\n");
      out.write("  		}\r\n");
      out.write("	  setTimeout(function(){ test(); });\r\n");
      out.write("	});\r\n");
      out.write("	\r\n");
      out.write("	$(window).on('resize', function(){\r\n");
      out.write("	  setTimeout(function(){ test(); }, 500);\r\n");
      out.write("	});\r\n");
      out.write("	\r\n");
      out.write("	$(\".navbar-toggler\").click(function(){\r\n");
      out.write("	  $(\".navbar-collapse\").slideToggle(300);\r\n");
      out.write("	  setTimeout(function(){ test(); });\r\n");
      out.write("	});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	// --------------add active class-on another-page move----------\r\n");
      out.write("/* 	jQuery(document).ready(function($){\r\n");
      out.write("	  // Get current path and find target link\r\n");
      out.write("	  var path = window.location.pathname.split(\"/\").pop();\r\n");
      out.write("	  \r\n");
      out.write("	  // Account for home page with empty path\r\n");
      out.write("	  if ( path == '' ) {\r\n");
      out.write("	    path = 'index.jsp';\r\n");
      out.write("	  }\r\n");
      out.write("	\r\n");
      out.write("	  var target = $('#navbarSupportedContent ul li a[href=\"'+path+'\"]');\r\n");
      out.write("	   if (target.length > 0) {\r\n");
      out.write("	    target.parent().addClass('active');\r\n");
      out.write("  		}\r\n");
      out.write("	}); */\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("	<button class=\"btn\" onClick=\"location.href='boardPage'\">게시판으로 이동</button>\r\n");
      out.write("	<script src=\"js/board.js\"></script>	\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/footer.css\">\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<footer class=\"new_footer_area bg_color\">\r\n");
      out.write("	<div class=\"new_footer_top\">\r\n");
      out.write("	    <div class=\"container\">\r\n");
      out.write("	        <div class=\"row\">\r\n");
      out.write("	            <div class=\"col-lg-3 col-md-6\">\r\n");
      out.write("	                <div class=\"f_widget company_widget wow fadeInLeft\" data-wow-delay=\"0.2s\" style=\"visibility: visible; animation-delay: 0.2s; animation-name: fadeInLeft;\">\r\n");
      out.write("	                    <h3 class=\"f-title f_600 t_color f_size_18\">Get in Touch</h3>\r\n");
      out.write("	                    <p>Don’t miss any updates of our new templates and extensions.!</p>\r\n");
      out.write("	                    <form action=\"#\" class=\"f_subscribe_two mailchimp\" method=\"post\" novalidate=\"true\" _lpchecked=\"1\">\r\n");
      out.write("	                        <input type=\"text\" name=\"EMAIL\" class=\"form-control memail\" placeholder=\"Email\">\r\n");
      out.write("	                        <button class=\"btn btn_get btn_get_two\" type=\"submit\">Subscribe</button>\r\n");
      out.write("	                        <p class=\"mchimp-errmessage\" style=\"display: none;\"></p>\r\n");
      out.write("	                        <p class=\"mchimp-sucmessage\" style=\"display: none;\"></p>\r\n");
      out.write("	                    </form>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"col-lg-3 col-md-6\">\r\n");
      out.write("	                <div class=\"f_widget about-widget pl_70 wow fadeInLeft\" data-wow-delay=\"0.4s\" style=\"visibility: visible; animation-delay: 0.4s; animation-name: fadeInLeft;\">\r\n");
      out.write("	                    <h3 class=\"f-title f_600 t_color f_size_18\">Download</h3>\r\n");
      out.write("	                    <ul class=\"list-unstyled f_list\">\r\n");
      out.write("	                        <li><a href=\"#\">Company</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Android App</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">ios App</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Desktop</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Projects</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">My tasks</a></li>\r\n");
      out.write("	                    </ul>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"col-lg-3 col-md-6\">\r\n");
      out.write("	                <div class=\"f_widget about-widget pl_70 wow fadeInLeft\" data-wow-delay=\"0.6s\" style=\"visibility: visible; animation-delay: 0.6s; animation-name: fadeInLeft;\">\r\n");
      out.write("	                    <h3 class=\"f-title f_600 t_color f_size_18\">Help</h3>\r\n");
      out.write("	                    <ul class=\"list-unstyled f_list\">\r\n");
      out.write("	                        <li><a href=\"#\">FAQ</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Term &amp; conditions</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Reporting</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Documentation</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Support Policy</a></li>\r\n");
      out.write("	                        <li><a href=\"#\">Privacy</a></li>\r\n");
      out.write("	                    </ul>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"col-lg-3 col-md-6\">\r\n");
      out.write("	                <div class=\"f_widget social-widget pl_70 wow fadeInLeft\" data-wow-delay=\"0.8s\" style=\"visibility: visible; animation-delay: 0.8s; animation-name: fadeInLeft;\">\r\n");
      out.write("	                    <h3 class=\"f-title f_600 t_color f_size_18\">Team Solutions</h3>\r\n");
      out.write("	                    <div class=\"f_social_icon\">\r\n");
      out.write("	                        <a href=\"#\" class=\"fab fa-facebook\"></a>\r\n");
      out.write("	                        <a href=\"#\" class=\"fab fa-twitter\"></a>\r\n");
      out.write("	                        <a href=\"#\" class=\"fab fa-linkedin\"></a>\r\n");
      out.write("	                        <a href=\"#\" class=\"fab fa-pinterest\"></a>\r\n");
      out.write("	                    </div>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </div>\r\n");
      out.write("	    </div>\r\n");
      out.write("	    <div class=\"footer_bg\">\r\n");
      out.write("	        <div class=\"footer_bg_one\"></div>\r\n");
      out.write("	        <div class=\"footer_bg_two\"></div>\r\n");
      out.write("	    </div>\r\n");
      out.write("	</div>\r\n");
      out.write("	<div class=\"footer_bottom\">\r\n");
      out.write("	    <div class=\"container\">\r\n");
      out.write("	        <div class=\"row align-items-center\">\r\n");
      out.write("	            <div class=\"col-lg-6 col-sm-7\">\r\n");
      out.write("	                <p class=\"mb-0 f_400\">© cakecounter Inc.. 2019 All rights reserved.</p>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"col-lg-6 col-sm-5 text-right\">\r\n");
      out.write("	                <p>Made with <i class=\"icon_heart\"></i> in <a href=\"http://cakecounter.com\" target=\"_blank\">CakeCounter</a></p>\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </div>\r\n");
      out.write("	    </div>\r\n");
      out.write("	</div>	\r\n");
      out.write("</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("</body>\r\n");
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
