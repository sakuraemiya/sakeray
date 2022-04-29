package lesein.proxy.jdkdynamicproxy;

/**
 * @author WangJie
 * @date 2022/4/29
 */
public class HelloApiImpl implements HelloApi{
    @Override
    public void say() {
        System.out.println("hello");
    }
}
