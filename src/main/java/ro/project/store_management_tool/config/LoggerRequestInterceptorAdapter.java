package ro.project.store_management_tool.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerRequestInterceptorAdapter implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerRequestInterceptorAdapter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        logger.info("Request processing started");
        logger.info("Method: {}", request.getMethod());
        logger.info("Headers:");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        logger.info("  {}: {}", headerName, request.getHeader(headerName))
                );
        mapHeadersToRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        logger.info("Request processing ended");
        logger.info("Status: {}", response.getStatus());
        MDC.clear();
    }

    private void mapHeadersToRequest(HttpServletRequest request) {
        MDC.put("trace_id", request.getHeader("TraceId"));
        MDC.put("username", request.getHeader("ApplicationUser"));
    }
}
