package practice.jpa.basic.entity.sample;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public final class GeneratorKeyValue {

    public static String generateUidValue() {
        return UUID.randomUUID().toString().replace("-", "");

    }

    public static String generateIdValue() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
