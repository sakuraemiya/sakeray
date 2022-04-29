package lesein.proxy.staticproxy;

import lesein.proxy.common.Common;

/**
 * @author WangJie
 * @date 2022/4/29
 */
public class StaticProxy implements UserApi {

    private UserApi userApi;

    //构造
    public StaticProxy(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public void save() {
        System.out.println(Common.BEGIN);
        userApi.save();
        System.out.println(Common.END);
    }
}
