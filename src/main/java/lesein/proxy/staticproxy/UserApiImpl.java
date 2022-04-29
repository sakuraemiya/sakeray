package lesein.proxy.staticproxy;

/**
 * @author WangJie
 * @date 2022/4/29
 */
public class UserApiImpl implements UserApi {

    @Override
    public void save() {
        System.out.println("保存用户信息");
    }
}
