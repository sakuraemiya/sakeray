package lesein.touchfish;

import java.util.stream.Stream;

/**
 * @author WangJie
 * @date 2022/5/17
 * @describe 进度条
 */
public class ProgressBar {
    public static void main(String[] args) throws InterruptedException {
        char incomplete = '░';
        char complete = '█';
        int total = 100;
        StringBuilder stringBuilder=new StringBuilder();
        Stream.generate(()->incomplete).limit(total).forEach(stringBuilder::append);
        for (int i = 0; i < total; i++) {
            stringBuilder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + stringBuilder;
            String percent = " " + (i + 1) + "%";
            System.out.print(progressBar + percent);
            Thread.sleep(i * 5L);
        }
    }
}
