import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

public class ReactorTest {
    @Test
    public void testMono() {
        //publish
        Mono<String> mono = Mono.just("hello word");
        //subscribe and exec
        mono.subscribe(new Consumer() {

            @Override
            public void accept(Object o) {
                System.out.println("fuck-->" + o);
            }

            @Override
            public Consumer andThen(Consumer after) {
                return null;
            }
        });
    }

    @Test
    public void testMonoMap() {
        //publish
        Mono<String> mono = Mono.just("hello word");
        Mono mono1 = mono.map(new Function<String, Object>() {
            @Override
            public Object apply(String s) {
                String ss = "fuck-->" + s;
                return ss;
            }
        });
        mono1.subscribe(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("fuck1-->" + o);
            }
        });
    }
}
