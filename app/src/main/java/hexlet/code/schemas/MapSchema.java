package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addPredicate("isRequired", o -> o instanceof Map);
    }

    @Override
    public final MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<Map> isSizeOf = o -> {
            return o.size() >= size;
        };
        addPredicate("sizeOf", isSizeOf);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> isRight = o -> {
            return schemas.keySet().stream()
                    .allMatch(k -> schemas.get(k).isValid(((Map) o).get(k)));
        };
        addPredicate("shape", isRight);
        return this;
    }
}
