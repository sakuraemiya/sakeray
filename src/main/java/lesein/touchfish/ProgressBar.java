package lesein.touchfish;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * @author WangJie
 * @date 2022/5/17
 * @describe 进度条
 */
public class ProgressBar {

    public static void main(String[] args) {
        progressBar("正在计算1+1...",10000L);
    }

    /**
     * @param method 要做的方法
     * @param time 做这件事情的耗时
     */
    public static void progressBar(String method,Long time) {
        AtomicBoolean flag = new AtomicBoolean(false);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            System.out.println(method);
            try {
                Thread.sleep(time);
                flag.set(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            char incomplete = '░';
            char complete = '█';
            int total = 100;
            StringBuilder stringBuilder = new StringBuilder();
            Stream.generate(() -> incomplete).limit(total).forEach(stringBuilder::append);
            for (int i = 0; i < total; i++) {
                stringBuilder.replace(i, i + 1, String.valueOf(complete));
                String progressBar = "\r" + stringBuilder;
                String percent = " " + (i + 1) + "%";
                if (flag.get()) {
                    //已经完成计算，加快进度条进度
                    System.out.print(progressBar + percent);
                } else {
                    //还未完成计算，进度条固定在99
                    if (Objects.equals(i, 99)) {
                        while (!flag.get()) {

                        }
                    }
                    System.out.print(progressBar + percent);
                    try {
                        Thread.sleep(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executorService.shutdown();
    }

}
