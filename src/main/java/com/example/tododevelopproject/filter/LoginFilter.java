package com.example.tododevelopproject.filter;

import com.example.tododevelopproject.dto.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.example.tododevelopproject.controller.UserController.LOGIN_USER;
import static com.example.tododevelopproject.exception.ErrorCode.UNAUTHORIZED;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/api/users/signup", "/api/users/login", "/api/users/logout"}; // 필터를 거치지 않는 RequestURI 지정

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        log.info("로그인 필터 로직 실행");
        log.info("request URI = {}", requestURI);

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(LOGIN_USER) == null) {
                // ObjectMapper를 통해 Dto를 HttpServletResponse에 담아서 JSON 형식으로 전달
                ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), UNAUTHORIZED, requestURI);
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(responseDto);

                httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write(json);

                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        // simpleMatch(String[] patterns, String str)
        // requestURI와 WHITE_LIST를 비교해서 일치 여부 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
