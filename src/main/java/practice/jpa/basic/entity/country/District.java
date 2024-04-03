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
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOWN_ID")
    @BatchSize(size = 500)
    @Builder.Default
    private List<Town> towns = new ArrayList<>();
}
