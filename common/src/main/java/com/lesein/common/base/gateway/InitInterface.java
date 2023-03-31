package com.lesein.common.base.gateway;

import com.lesein.common.base.aop.RestConfiguration;
import com.lesein.common.base.gateway.entity.GatewayUrl;
import com.lesein.common.base.util.CollectionUtil;
import com.lesein.common.base.util.JacksonUtil;
import org.reflections8.Reflections;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author WangJie
 * @date 2023/3/31
 */
public class InitInterface {

    @Value("${spring.application.name}")
    private String id;
    /**
     * 支持扫描自定义包路径
     */
    @Value("${gateway.interface.scan.package:com.lesein}")
    private String scanPackage;

    @PostConstruct
    private void init() {
        initGateway();
    }

    /**
     * 初始化网关地址
     */
    private void initGateway(){
        //扫描包下的所有类
        Reflections reflections = new Reflections(scanPackage);
        List<GatewayUrl> registerList = getRegisterList(reflections);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("filename.txt");
            myWriter.write(JacksonUtil.toJson(registerList));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取真实网关地址
     * @param reflections
     * @return
     */
    private List<GatewayUrl> getRegisterList(Reflections reflections){
        List<GatewayUrl> gatewayUrlLists = new ArrayList<>();
        //获取包含RestConfiguration注解的所有类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(RestConfiguration.class);
        if(CollectionUtil.isNotEmpty(typesAnnotatedWith)){
            typesAnnotatedWith.forEach(clazz->{
                Set<Method> methods = new HashSet<>(Arrays.asList(clazz.getDeclaredMethods()));
                //过滤Object类自带的方法
                methods.removeIf(method -> method.getDeclaringClass().equals(Object.class));
                for(Method method:methods){
                    GatewayUrl gatewayUrl=new GatewayUrl();
                    gatewayUrl.setUniqueId(id);
                    gatewayUrl.setPath("/" + clazz.getSimpleName() + "/" + method.getName());
                    gatewayUrlLists.add(gatewayUrl);
                }
            });
        }
        return gatewayUrlLists;
    }
}
