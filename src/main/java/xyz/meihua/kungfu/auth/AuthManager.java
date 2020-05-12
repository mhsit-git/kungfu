package xyz.meihua.kungfu.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import xyz.meihua.kungfu.model.vo.LoginInfoVO;

import java.util.concurrent.TimeUnit;

/**
 * @author meihua
 * @date 2020/05/11
 */
@Component
@Slf4j
public class AuthManager {

    private final RedisTemplate redisTemplate;


    public AuthManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Nullable
    public LoginInfoVO get(String token) {
        Object obj = redisTemplate.opsForValue().get(makeTokenKey(token));
        if (obj == null) {
            return null;
        }
        return ((JSONObject) obj).toJavaObject(LoginInfoVO.class);
    }


    public void login(String token, LoginInfoVO loginInfo) {
        redisTemplate.opsForValue().set(makeTokenKey(token), loginInfo, 3, TimeUnit.DAYS);
    }


    public void removeAllToken(Long userId) {
        ListOperations listOperations = redisTemplate.opsForList();
//        listOperations.

        //TODO 踢出用户登陆


    }


    private String makeTokenKey(String token) {
        return "TOKEN:" + token;
    }

}
