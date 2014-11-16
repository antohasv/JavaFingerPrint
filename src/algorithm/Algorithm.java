package algorithm;

public abstract class Algorithm<Result> {

    protected abstract Result execute();

    public Result executeWithTime() {
        long start = System.currentTimeMillis();
        Result result = execute();
        System.out.println(getClass().getName() + ": " + (System.currentTimeMillis() - start) + " ms.");
        return result;
    }
}
