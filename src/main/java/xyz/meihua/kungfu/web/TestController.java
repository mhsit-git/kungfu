package xyz.meihua.kungfu.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.meihua.kungfu.model.vo.LoginInfoVO;
import xyz.meihua.kungfu.model.vo.UserInfoVO;
import xyz.meihua.kungfu.model.vo.WebResult;
import xyz.meihua.kungfu.model.vo.request.LoginRequest;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author meihua
 * @date 2020/05/11
 */
@RestController
public class TestController {
    private final RedisTemplate<String,String> redisTemplate;

    public TestController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("test1")
    @GetMapping("/test1")
    WebResult<String> test1() {
        redisTemplate.opsForValue().set("abc","123",5, TimeUnit.SECONDS);
        return WebResult.success();
    }


}
