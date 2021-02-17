import java.util.concurrent.atomic.AtomicInteger;

public class IncrementalSequenceGenerator implements SequenceGenerator {
    private AtomicInteger index = new AtomicInteger();

    @Override
    public String generate() {
        return String.valueOf(index.incrementAndGet());
    }
}
