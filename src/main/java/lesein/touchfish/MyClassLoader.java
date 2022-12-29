package lesein.touchfish;

/**
 * @author WangJie
 * @date 2022/12/20
 */
public class MyClassLoader {
    public static void main(String[] args) {
        try {
            System.out.println(Class.forName("lesein.touchfish.MyClassLoader").getClassLoader());

            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            System.out.println(systemClassLoader.loadClass("lesein.touchfish.MyClassLoader").getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
