package practice.jpa.join_method.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostImageDTO {

    private String userName;
    private String postName;
    private String imageName;
}
