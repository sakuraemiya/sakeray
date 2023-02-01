package lesein.rpc.provider.impl;

import lesein.rpc.provider.api.HelloApi;

public class HelloApiImpl implements HelloApi {
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
