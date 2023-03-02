package cn.com.webxml;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author WangJie
 * @date 2023/3/1
 */
public class Main {
    public static void main(String[] args) {
        WeatherWebService factory=new WeatherWebService();
        WeatherWebServiceSoap service=factory.getWeatherWebServiceSoap();
        ArrayOfString strs=service.getWeatherbyCityName("北京");
        List<String> lists=strs.getString();
//        for (String string : lists) {
//            System.out.println(string);
//        }
        axis();
    }

    public static void axis() {
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            //设置地址
            call.setTargetEndpointAddress("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl");
            call.setUseSOAPAction(true);

            //域名加方法,//上面有写着targetNamespace="http://x.x.x/",这个就是你的命名空间值了;加方法名
            call.setSOAPActionURI("http://WebXml.com.cn/" + "ValidateEmailAddress");


            // 设置要调用哪个方法
            call.setOperationName(new QName("http://WebXml.com.cn/", "ValidateEmailAddress"));
            //设置参数名 :参数名 ,参数类型:String, 参数模式：'IN' or 'OUT'
            call.addParameter(new QName("http://WebXml.com.cn/", "theEmail"),
                    org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

            call.setEncodingStyle("UTF-8");
            //返回类型
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);

            // 调用方法并传递参数
            String res = String.valueOf(call.invoke(new Object[]{"wangkanglu1024@163.com"}));
            System.out.println(res);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
