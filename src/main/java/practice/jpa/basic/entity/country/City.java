package practice.jpa.basic.entity.country;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    @BatchSize(size = 500)
    @Builder.Default
    private List<District> districts = new ArrayList<>();
}
