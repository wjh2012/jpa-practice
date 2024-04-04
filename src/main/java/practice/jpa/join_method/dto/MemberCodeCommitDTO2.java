package practice.jpa.join_method.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCodeCommitDTO2 {
    private String memberName;
    private List<String> codeNames;
    private List<String> commitNames;
}
