package lesein.touchfish;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WangJie
 * @date 2023/2/1
 */
public class MySingletonMode {

    private volatile static MySingletonMode instance;

    public static MySingletonMode getInstance() {
        if (instance == null) {
            synchronized (MySingletonMode.class) {
                if (instance == null){
                    System.out.println("直接进行一个初始化");
                    instance = new MySingletonMode();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<20;i++){
            executorService.execute(MySingletonMode::getInstance);
        }
    }
}
