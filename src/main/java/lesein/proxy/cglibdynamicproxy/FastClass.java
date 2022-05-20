package lesein.proxy.cglibdynamicproxy;

/**
 * @author WangJie
 * @Date 2022/5/20
 * @describe FastClass机制 通过对Class对象进行特殊处理 为每一个方法建立一个索引 每次代理对象方法的调用都是通过索引寻找初始对象直接进行调用
 */
public class FastClass {
    public static void main(String[] args) {
        OriginObject originObject=new OriginObject();
        ProxyObject proxyObject=new ProxyObject();
        int index=proxyObject.getIndex("say()");
        proxyObject.invoke(index,originObject,null);
    }
}
//初始对象
class OriginObject{
    public void say(){
        System.out.println("say");
    }
    public void read(){
        System.out.println("read");
    }
}
//代理对象
class ProxyObject{
    public Object invoke(int index,Object o,Object[] args){
        OriginObject originObject=(OriginObject) o;
        switch (index){
            case 1:
                originObject.say();
                return null;
            case 2:
                originObject.read();
                return null;
        }
        return null;
    }
    public int getIndex(String method){
        switch(method.hashCode()){
            case 109212204:
                return 1;
            case -934981353:
                return 2;
        }
        return -1;
    }
}
