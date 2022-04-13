import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long time = new Date().getTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> rootTask = forkJoinPool.submit(new SumForkJoinTask(1L, 10_0000_0000L));
        System.out.println("计算结果：" + rootTask.get());
        System.out.println( new Date().getTime() - time);
    }
}

class SumForkJoinTask extends RecursiveTask<Long> {
    private final Long min;
    private final Long max;
    private Long threshold = 1000L;

    public SumForkJoinTask(Long min, Long max) {
        this.min = min;
        this.max = max;
    }
    @Override
    protected Long compute() {
        // 小于阈值时直接计算
        if ((max - min) <= threshold) {
            long sum = 0;
            for (long i = min; i < max; i++) {
                sum = sum + i;
            }
            return sum;
        }
        // 拆分成小任务
        long middle = (max + min) >>> 1;
        SumForkJoinTask leftTask = new SumForkJoinTask(min, middle);
        leftTask.fork();
        SumForkJoinTask rightTask = new SumForkJoinTask(middle, max);
        rightTask.fork();
        // 汇总结果
        return leftTask.join() + rightTask.join();
    }
}