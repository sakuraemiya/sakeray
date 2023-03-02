package lesein.touchfish;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author WangJie
 * @date 2023/2/27
 */
public class SemaphoreSecKill {
    /**
     * 代替redis里面有10个商品，单机版
     */
    private  static Semaphore semaphore=new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            Thread thread=new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName()+"下单成功");
                }catch (Exception e){
                    e.printStackTrace();
                }
            },i+"号客户");
            thread.start();
        }
    }
}
