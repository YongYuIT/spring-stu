package hello.gateway_demo.gateway;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class RequestBodyCachePredicate implements Predicate {
    @Override
    public boolean test(Object o) {
        return true;
    }
}
