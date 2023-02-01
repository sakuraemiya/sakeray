package lesein.rpc.framework.protocal.http;

import lesein.rpc.framework.Invocation;
import lesein.rpc.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){
        try {
            //反序列化，dubbo框架会做类似操作
            Invocation invocation= (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            //知道调用方要调用的接口名称（需要去提供方找到此名称对应的方法，此处便需要有一个类似注册中心的东西）
            String interfaceName = invocation.getInterfaceName();
            //找到接口对应的类
            Class clazz = LocalRegister.get(interfaceName);
            //根据方法名和参数类型找到对应的方法
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamType());
            //执行方法,第一个参数传入当前类，第二参数传入入参
            String result = (String) method.invoke(clazz.newInstance(), invocation.getParams());
            //将结果写入response
            IOUtils.write(result,resp.getOutputStream(), Charset.defaultCharset());
        } catch (ClassNotFoundException | IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
