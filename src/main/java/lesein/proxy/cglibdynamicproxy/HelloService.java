package lesein.proxy.cglibdynamicproxy;

/**
 * @author WangJie
 * @date 2022/5/19
 */
public class HelloService {

    public HelloService(){

    }

    /**
     * final修饰的方法不能被代理
     */
    public final void sayBye(){
        System.out.println("sayBye");
    }

    public void sayHello(){
        System.out.println("sayHello");
    }
}
