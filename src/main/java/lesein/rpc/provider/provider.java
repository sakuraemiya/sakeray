package lesein.rpc.provider;

import lesein.rpc.framework.MyURL;
import lesein.rpc.framework.protocal.http.HttpServer;
import lesein.rpc.framework.register.LocalRegister;
import lesein.rpc.framework.register.RegisterCenter;
import lesein.rpc.provider.api.HelloApi;
import lesein.rpc.provider.impl.HelloApiImpl;

public class provider {
    public static void main(String[] args) {
        //启动服务时，向本地注册服务
        LocalRegister.register(HelloApi.class.getName(), HelloApiImpl.class);
        //向注册中心注册,第一个参数：dubbo通过java.net拿到本机的ip地址，第二个参数：用户配置的端口号
        MyURL myURL=new MyURL("localhost",8080);
        RegisterCenter.register(HelloApi.class.getName(),myURL);

        HttpServer httpServer=new HttpServer();
        httpServer.start(myURL.getHostName(),myURL.getPort());
    }
}
