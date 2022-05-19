package lesein.proxy.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author WangJie
 * @date 2022/5/19
 */
public class Main {
    public static void main(String[] args) {
        //通过cglib动态代理获取代理对象
        Enhancer enhancer=new Enhancer();
        //设置enhancer的父对象
        enhancer.setSuperclass(HelloService.class);
        //设置enhancer的回调对象
        enhancer.setCallback(new CglibDynamicProxy());
        //创建代理对象
        HelloService proxy=(HelloService) enhancer.create();
        //无法代理final方法
        proxy.sayBye();

        proxy.sayHello();
    }
}
