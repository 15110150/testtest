package server.crm.helper;

import java.util.Optional;
import java.util.function.Consumer;

public class CustomOptional<T> {
    private Optional<T> optional;
    public CustomOptional(Optional<T> optional){
        this.optional=optional;
    }
    public static <T> CustomOptional<T> of(Optional<T> optional) {
        return new CustomOptional<>(optional);
    }

    public CustomOptional<T> ifPresent(Consumer<T> c) {
        optional.ifPresent(c);
        return this;
    }

    public CustomOptional<T> ifNotPresent(Runnable r) {
        if (!optional.isPresent()) {
            r.run();
        }
        return this;
    }
}
