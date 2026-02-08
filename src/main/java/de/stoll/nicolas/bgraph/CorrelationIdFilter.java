package de.stoll.nicolas.bgraph;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CorrelationIdFilter implements Filter {

    public static final String CORRELATION_ID_KEY = "correlationId";
    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String correlationId = Optional.ofNullable(httpRequest.getHeader(CORRELATION_ID_HEADER))
                .orElseGet(() -> java.util.UUID.randomUUID().toString());

        MDC.put(CORRELATION_ID_KEY, correlationId);

        httpResponse.setHeader("X-Correlation-Id", correlationId);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(CORRELATION_ID_KEY);
        }
    }
}
