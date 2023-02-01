package lesein.rpc.framework;

import java.util.List;
import java.util.Random;

public class LoadBalance {
    public static MyURL random(List<MyURL> list){
        Random random=new Random();
        int n=random.nextInt(list.size());
        return list.get(n);
    }
}
