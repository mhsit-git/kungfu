package xyz.meihua.kungfu;

import org.junit.jupiter.api.Test;

/**
 * @author meihua
 * @date 2020/05/11
 */
public class JdkTest {

    @Test
    public void test1() {
        int aaa = sw("aaa");
        System.out.println(aaa);
        sw2("aaa");
    }

    public int sw(String s) {
        return switch (s) {
            case "1" -> 1;
            case "2" -> 2;
//            default -> {
//                yield s.length();
//            }
            default -> s.length();
        };
    }

    public void sw2(String s) {
        switch (s) {
            case "1" -> System.out.println("123");
            case "2" -> System.out.println("245");
            default -> System.out.println(s);
        }
    }

//    @Test
//    public void test2() {
//        String html = """
//                <html>
//                  <body>
//                    <p>Hello, world</p>
//                  </body>
//                </html>
//                """;
//        System.out.println(html);
//    }


    @Test
    public void test3() {
        var a = "aaaa";
        System.out.println(a);
    }
}
