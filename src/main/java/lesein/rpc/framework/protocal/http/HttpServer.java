package lesein.rpc.framework.protocal.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    /**
     * 启动tomcat，收到请求，交给dispatcherServlet处理
     * @param hostName
     * @param port
     */
    public void start(String hostName,Integer port){
        //启动内嵌tomcat
        Tomcat tomcat=new Tomcat();
        Server server=tomcat.getServer();

        Service service = server.findService("Tomcat");

        Connector connector=new Connector();
        connector.setPort(port);

        Engine engine=new StandardEngine();
        engine.setDefaultHost(hostName);

        Host host=new StandardHost();
        host.setName(hostName);

        String contextPath="";
        Context context=new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        //添加dispatcherServlet
        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
