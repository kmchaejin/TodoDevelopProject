package com.example.tododevelopproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;
import static com.example.tododevelopproject.controller.UserController.LOGIN_USER;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/api/users/signup", "/api/users/login", "/api/users/logout"}; // 필터를 거치지 않는 RequestURI 지정

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; // 다운캐스팅
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");
        log.info("request URI = {}", requestURI);

        // WHITE_LIST에 없는 필터는 세션 검증
        if(!isWhiteList(requestURI)){
            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute(LOGIN_USER) == null){
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI){
        // simpleMatch(String[] patterns, String str)
        // requestURI와 WHITE_LIST를 비교해서 일치 여부 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
