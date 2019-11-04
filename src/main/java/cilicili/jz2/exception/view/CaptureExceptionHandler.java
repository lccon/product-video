package cilicili.jz2.exception.view;

import cilicili.jz2.exception.domain.Result;
import cilicili.jz2.exception.facade.ExceptionHandlerFacade;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CaptureExceptionHandler extends AbstractHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		HandlerMethod mathod = (HandlerMethod) handler;
		ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
		if (body == null) {
			modelAndView.setViewName("/500.html");
		}
		Result result = ExceptionHandlerFacade.handleException(ex);
		try {
			response.setContentType("application/json;charset=utf-8");
			java.io.PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(result));
			out.println();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

}
