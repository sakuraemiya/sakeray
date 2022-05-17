package lesein.proxy.cglibdynamicproxy;

import lesein.proxy.common.Common;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author WangJie
 * @date 2022/5/17
 */
public class CglibDynamicProxy implements MethodInterceptor {

    /**
     * @param o cglib生成的代理对象
     * @param method 被代理的对象方法
     * @param objects 入参
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(Common.BEGIN);
        // 执行相应的目标方法
        Object rs = methodProxy.invokeSuper(o, objects);
        System.out.println(Common.END);
        return rs;
    }
}
