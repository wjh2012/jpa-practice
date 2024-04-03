package practice.jpa.mapping.oneway.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildDTO {
    private String id;
    private String name;

    public static ChildDTO fromEntity(ChildDTO entity) {
        return ChildDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
