package lesein.proxy.jdkdynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @author WangJie
 * @date 2022/4/29
 */
public class Main {
    public static void main(String[] args) {
        //首先获取需要动态代理的类，然后通过代理类获取构造函数，并传入参数InvocationHandler.class，通过构造函数来创建动态代理的对象，将自定义的InvocationHandler实例传入
        HelloApi helloApi = (HelloApi) Proxy.newProxyInstance(HelloApi.class.getClassLoader(), new Class[]{HelloApi.class}, new JdkDynamicProxy(new HelloApiImpl()));
        helloApi.say();
    }
}
