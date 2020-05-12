package xyz.meihua.kungfu.model.vo;

import lombok.Data;

/**
 * @author meihua
 * @date 2020/05/11
 */
@Data
public class WebResult<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;
    private T data;
    private final long timestamp = System.currentTimeMillis();
    private Integer status;
    private String message;

    private WebResult() {
    }

    public WebResult(T data, Integer status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> WebResult<T> successData(T data) {
        return new WebResult<>(data, SUCCESS, null);
    }

    public static <T> WebResult<T> error(String message) {
        return new WebResult<>(null, ERROR, message);
    }

    public static <T> WebResult<T> success(T data, Integer status, String message) {
        return new WebResult<>(data, status, message);
    }

    public static <T> WebResult<T> success() {
        return new WebResult<>(null, SUCCESS, null);
    }
}
