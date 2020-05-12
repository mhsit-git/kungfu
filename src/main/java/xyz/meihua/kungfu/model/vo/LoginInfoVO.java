package xyz.meihua.kungfu.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author meihua
 * @date 2020/05/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoVO {
    private Long userId;
    private String userName;
    private Date lastLogin;
    private String token;
}
