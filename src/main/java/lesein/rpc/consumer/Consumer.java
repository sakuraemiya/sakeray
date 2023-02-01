package lesein.rpc.consumer;


import lesein.rpc.framework.ProxyFactory;
import lesein.rpc.provider.api.HelloApi;

public class Consumer {
    public static void main(String[] args) {

        /**
         * HelloApi helloApi=??;
         * String result = helloApi.sayHello("Sakeray");
         * System.out.println(result);
         * 在服务消费方，只能依赖HelloApi接口，但是我们需要HelloApi这个接口的对象
         * 第一种方式：通过这个接口的实现类new出对象；第二种方法：动态代理，基于接口生成代理对象
         */
        HelloApi helloApi = ProxyFactory.getProxy(HelloApi.class);
        String result1 = helloApi.sayHello("Sakeray");
        System.out.println(result1);
    }
}
