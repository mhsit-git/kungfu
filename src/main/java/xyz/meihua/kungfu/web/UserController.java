package xyz.meihua.kungfu.web;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.meihua.kungfu.auth.AuthManager;
import xyz.meihua.kungfu.annotation.Login;
import xyz.meihua.kungfu.model.vo.LoginInfoVO;
import xyz.meihua.kungfu.model.vo.UserInfoVO;
import xyz.meihua.kungfu.model.vo.WebResult;
import xyz.meihua.kungfu.model.vo.request.LoginRequest;

import java.util.Date;
import java.util.UUID;

/**
 * @author meihua
 * @date 2020/05/11
 */
@Slf4j
@RestController
public class UserController {

    private final AuthManager authManager;

    public UserController(AuthManager authManager) {
        this.authManager = authManager;
    }
    @ApiOperation("登录")
    @PostMapping("/login")
    WebResult<LoginInfoVO> login(@RequestBody LoginRequest request) {
        String token = UUID.randomUUID().toString();
        LoginInfoVO loginInfoVO = LoginInfoVO.builder()
                .userName("张三")
                .lastLogin(new Date())
                .token(token)
                .userId(123456L)
                .build();

        this.authManager.login(token, loginInfoVO);
        return WebResult.successData(loginInfoVO);
    }

    @ApiOperation("用户")
    @Login
    @GetMapping("/user")
    WebResult<UserInfoVO> login(@RequestParam(value = "token",required = false) String token) {
        LoginInfoVO loginInfoVO = authManager.get(token);
        log.info("{}", loginInfoVO);
        UserInfoVO userInfoVO = UserInfoVO.builder()
                .userId(123456L)
                .userName("张三")
                .lastLogin(new Date())
                .build();
        if (!token.equals("aa")) {
            throw new RuntimeException("完蛋了呀！！");
        }
        return WebResult.successData(userInfoVO);
    }


}
