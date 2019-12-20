package cilicili.jz2.web.filter;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.constant.PermissionConstant;
import cilicili.jz2.service.LoginService;
import cilicili.jz2.utils.CookieUtil;
import cilicili.jz2.utils.IpAddressUtil;
import cilicili.jz2.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 登录拦截
 *
 * @Date:2019/12/11
 * @Author:lc
 */
public class LoginSessionFilter implements Filter {

    private LoginService loginService;

    private FilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        request.setAttribute("randomTime", System.currentTimeMillis());
        response.addHeader("X-Frame-Options","SAMEORIGIN");

        if (uri.endsWith(".swf") || uri.endsWith(".jpg") || uri.endsWith(".JPG") || uri.endsWith(".gif")
                || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".bmp")
                || uri.endsWith(".ico") || uri.endsWith("1.txt") || uri.endsWith(".amr") || uri.endsWith(".mp3")
                || uri.endsWith(".apk") || uri.toLowerCase().endsWith(".doc") || uri.toLowerCase().endsWith(".docx")
                || uri.toLowerCase().endsWith(".pdf") || uri.toLowerCase().endsWith(".txt")
                || uri.toLowerCase().endsWith(".ppt") || uri.toLowerCase().endsWith(".pptx")
                || uri.toLowerCase().endsWith(".xls") || uri.toLowerCase().endsWith(".xlsx")
                || uri.toLowerCase().endsWith(".map") || uri.toLowerCase().endsWith(".htm")
                || uri.contains("/noLogin")) {
            chain.doFilter(request, response);
            ThreadVariable.clearThreadVariable();
            return;
        }

        // 无需登录验证
        if (isNotLoginValidate(uri)) {
            chain.doFilter(request, response);
            ThreadVariable.clearThreadVariable();
            return;
        }

        String sid = CookieUtil.getSessionIdFromCookies(request);
        Boolean login = getLoginService().isLogin(sid, IpAddressUtil.getIpAddr(request), request.getRequestURI());
        if (login) {
            CookieUtil.putSessionIdInCookies(request, response, PermissionConstant.LOGIN_SESSION_ID, sid);
            chain.doFilter(request, response);
            ThreadVariable.clearThreadVariable();
            return;
        } else  {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("未登录或登录已失效，请登录后查看");
            ThreadVariable.clearThreadVariable();
            return;
        }
    }

    private boolean isNotLoginValidate(String uri) {
        if("/".equals(uri) || "/api/video/show".equals(uri) || "/api/now".equals(uri)
                || "/api/login".equals(uri) || "/api//user/add".equals(uri) || "/api/video/find".equals(uri)) {
            return true;
        }
        return false;
    }

    private LoginService getLoginService() {
        if (loginService == null) {
            loginService = (LoginService) SpringBeanUtil.getBeanFromSpringByBeanName("loginService");
        }
        return loginService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {}
}
