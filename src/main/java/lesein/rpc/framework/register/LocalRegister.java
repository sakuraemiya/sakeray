package lesein.rpc.framework.register;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {

    private static Map<String,Class> map=new HashMap<>();

    public static void register(String interfaceName,Class impClass){
        map.put(interfaceName,impClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
