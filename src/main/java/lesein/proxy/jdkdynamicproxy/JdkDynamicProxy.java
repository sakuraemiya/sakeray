package lesein.proxy.jdkdynamicproxy;

import lesein.proxy.common.Common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author WangJie
 * @date 2022/4/29
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object object;

    public JdkDynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(Common.BEGIN);
        // 执行相应的目标方法
        Object rs = method.invoke(object, args);
        System.out.println(Common.END);
        return rs;
    }
}
