package xyz.meihua.kungfu.model.vo.request;

import lombok.Data;

/**
 * @author meihua
 * @date 2020/05/11
 */
@Data
public class LoginRequest {
    private String loginName;
    private String password;
}
