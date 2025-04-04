package com.example.tododevelopproject.filter;

import com.example.tododevelopproject.dto.common.ErrorResponseDto;
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

import static com.example.tododevelopproject.config.Const.LOGIN_USER;
import static com.example.tododevelopproject.exception.ErrorCode.UNAUTHORIZED;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/api/users/signup", "/api/users/login", "/api/users/logout"}; // 필터를 거치지 않는 RequestURI 지정

    /*
    유효한 세션이 존재하면 다음 필터(없다면 컨트롤러)로 전달
    세션이 존재하지 않으면 클라이언트로 ErrorResponseDto를 직렬화하여 응답
     */
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
                // ObjectMapper를 통해 Dto를 JSON 형식으로 직렬화
                ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), UNAUTHORIZED.getStatus(), UNAUTHORIZED.getError(), UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage(), requestURI);
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(responseDto);

                // response 설정
                httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write(json);

                return;
            }
        }

        chain.doFilter(request, response);
    }

    /*
    requestURI와 WHITE_LIST를 비교해서 일치 여부 반환
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
