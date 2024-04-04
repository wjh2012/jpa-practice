package practice.jpa.mapping.oneway.ManyToOne;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDTO {

    private String id;
    private String name;
    private List<ChildDTO> children;

    public static ParentDTO fromEntity(ParentDTO entity) {
        return ParentDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }
}
