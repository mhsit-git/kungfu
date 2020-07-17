package xyz.meihua.kungfu.exception;

import lombok.Getter;

/**
 * @author meihua
 * @date 2020/05/13
 */
@Getter
public class BusinessException extends RuntimeException {
    private final static Integer GENERAL_EXCEPTION_CODE = 100;
    private final Integer code;
    private final String message;


    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        super(message);
        code = GENERAL_EXCEPTION_CODE;
        this.message = message;
    }
}
