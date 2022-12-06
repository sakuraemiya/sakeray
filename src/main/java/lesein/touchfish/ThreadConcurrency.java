package lesein.touchfish;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WangJie
 * @date 2022/10/21
 */
public class ThreadConcurrency {
    public static int maxNum = 5;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.submit(() -> {
                if (placeOrder()) {
                    System.out.println("线程" + j + "抢到了产品");
                }
            });
        }
        executorService.shutdown();
    }

    public static boolean placeOrder() {
        if (maxNum > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            maxNum--;
            return true;
        }
        return false;
    }
}
