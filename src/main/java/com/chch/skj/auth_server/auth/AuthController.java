package com.chch.skj.auth_server.auth;

import com.chch.skj.auth_server.auth.res.ResponseEntityInnerDto;
import com.chch.skj.auth_server.auth.req.UserLoginReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chch.skj.auth_server.auth.res.JWTPair;
import com.chch.skj.auth_server.auth.res.UserRes;
import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {


    private final AuthService authService;

    //Login 성공 시, 쿠키로 설정해라 + 데이터 응답
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReq userLoginDO, HttpServletResponse response) throws UserVerificationFailException {


        String id = userLoginDO.getUserID();
        String password = userLoginDO.getPassWord();

        //region 입력값 검증
        if (id == null || id.isBlank()) {
            return ResponseEntityInnerDto.fromToResEntity("Login 실패", HttpStatus.BAD_REQUEST, "ID 가 비어있습니다.");

        } else if (password == null || password.isBlank()) {
            return ResponseEntityInnerDto.fromToResEntity("Login 실패", HttpStatus.BAD_REQUEST, "Password가 비어있습니다.");
        }
        //endregion 입력값 검증


        JWTPair jwtResult;

        jwtResult = authService.login(id, password);

        var cookie = new Cookie("authToken", String.format("Bearer %s", jwtResult.authToken()));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 30);//30분
        cookie.setSecure(true);

        response.addCookie(cookie);


        /* //계속 client측에서 server로 주는 것은 안전하지 않음으로 제거
        cookie = new Cookie("refreshToken", String.format("Bearer %s", jwtResult.refreshToken()));
        cookie.setMaxAge(60 * 60 * 24);//하루
        cookie.setSecure(true);
        */

        response.addCookie(cookie);


        return ResponseEntityInnerDto.fromToResEntity(jwtResult, HttpStatus.OK, "Login 성공");


    }

    //JWT 값을 받아서 토큰 검증 및 유저값을 넘겨준다.
    @GetMapping(path = "/valid")
    public ResponseEntity<?> valid(HttpServletRequest request, HttpServletResponse response) throws UserVerificationFailException {

        String authToken = "";
        String refreshToken = "";

        //region 쿠키에서 값 가져오기
        for (var cookie : request.getCookies()) {
            String cookieName = cookie.getName();

            if ("authToken".equals(cookieName)) {
                authToken = cookie.getValue();
            }
        }
        //endregion 쿠키에서 값 가져오기

        boolean isAuthenticated = false;

        UserRes userDo = null;

        if (authToken != "") {
            int splitIndex = authToken.indexOf(" ");
            if (splitIndex != -1) {
                String tokenUsage = authToken.substring(0, splitIndex);
                String token = authToken.substring(splitIndex, authToken.length());

                userDo = authService.isValid(authToken);

                if (userDo == null) {
                    return ResponseEntityInnerDto.fromToResEntity(userDo,HttpStatus.OK,"Auth 인증 실패");
                } else {
                    isAuthenticated = true;
                    return ResponseEntityInnerDto.fromToResEntity(userDo,HttpStatus.OK,"Auth 인증 성공","authToken 성공");
                }
            }
        }


        return null;
    }
}
