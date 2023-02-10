package lesein.touchfish;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author WangJie
 * @date 2023/2/10
 */
public class MyCompletableFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CommonService commonService = new CommonService();
        long start = System.currentTimeMillis();
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                return commonService.getFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        Thread.sleep(300);
        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
            try {
                return commonService.getSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        System.out.println(first.get());
        System.out.println(second.get());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class CommonService {

    public String getFirst() throws InterruptedException {
        Thread.sleep(500);
        return "第一";
    }

    public String getSecond() throws InterruptedException {
        Thread.sleep(500);
        return "第二";
    }
}

