package lesein.rpc.framework;

import lesein.rpc.framework.protocal.http.HttpClient;
import lesein.rpc.framework.register.RegisterCenter;


import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass){
        //JDK动态代理
        Object proxyInstance = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            HttpClient httpClient=new HttpClient();
            Invocation invocation=new Invocation(interfaceClass.getName(),method.getName(),args,method.getParameterTypes());
            List<MyURL> list = RegisterCenter.get(interfaceClass.getName());
            //通过负载均衡找到一个url发起调用
            MyURL url = LoadBalance.random(list);
            return httpClient.send(url.getHostName(), url.getPort(), invocation);
        });
        return (T)proxyInstance;
    }
}
