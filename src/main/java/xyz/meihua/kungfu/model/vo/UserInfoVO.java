package xyz.meihua.kungfu.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author meihua
 * @date 2020/05/11
 */
@Data
@Builder
public class UserInfoVO {
    private Long userId;
    private String userName;
    private Date lastLogin;
}
