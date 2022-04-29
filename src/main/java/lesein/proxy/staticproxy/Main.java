package lesein.proxy.staticproxy;

/**
 * @author WangJie
 * @date 2022/4/29
 */
public class Main {
    public static void main(String[] args) {
        UserApiImpl userApiImpl = new UserApiImpl();
        UserApi userApi = new StaticProxy(userApiImpl);
        userApi.save();
    }
}
