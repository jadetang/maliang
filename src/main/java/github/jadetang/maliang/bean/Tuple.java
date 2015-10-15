package github.jadetang.maliang.bean;

/**
 * @Author Tang Sicheng
 */
public class Tuple<T, S> {
    private T first;
    private S second;

    public Tuple(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T _1() {
        return first;
    }

    public S _2() {
        return second;
    }
}
