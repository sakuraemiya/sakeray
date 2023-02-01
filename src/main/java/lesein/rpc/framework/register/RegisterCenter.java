package lesein.rpc.framework.register;

import lesein.rpc.framework.MyURL;

import java.io.*;
import java.util.*;

public class RegisterCenter { //like zookeeper,对应list url表明有多台服务器

    private static Map<String, List<MyURL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, MyURL myURL) {
        List<MyURL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(myURL);
        REGISTER.put(interfaceName, list);

        //存入文件，因为consumer和provider属于两个不同的进程，虽然provider启动时将url写入注册中心，但是当consumer启动时，进程内的注册中心仍然是一个空文件
        saveFile();
    }

    public static List<MyURL> get(String interfaceName) {
        REGISTER = getFile();

        return REGISTER.get(interfaceName);
    }

    public static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/zk.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<MyURL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/zk.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<MyURL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
