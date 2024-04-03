package practice.jpa.oracle_standard;

import java.util.UUID;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class GeneratorKeyValue {

    public static String generateUidValue() {
        return UUID.randomUUID().toString().replace("-", "");

    }

    public static String generateIdValue() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
