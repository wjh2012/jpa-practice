package practice.jpa.join_method.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCodeCommitDTO {
    private String memberName;
    private String codeName;
    private String commitName;
}
