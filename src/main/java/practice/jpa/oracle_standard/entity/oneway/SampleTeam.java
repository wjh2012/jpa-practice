package practice.jpa.oracle_standard.entity.oneway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import practice.jpa.oracle_standard.BaseEntity;
import practice.jpa.oracle_standard.GeneratorKeyValue;

@Entity
@Table(name = "TB_SAMPLE_TEAM") // 실제 데이터베이스 테이블명
@Builder(toBuilder = true) // 작동 안할 시 내부에 작성
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(callSuper = true)
public class SampleTeam extends BaseEntity {

    /**
     * ID
     */
    @Id
    @Column(name = "SAMPLE_TEAM_ID", nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TB_SAMPLE_TEAM_PK_SEQ_GENERATOR")
    @SequenceGenerator(
            name = "TB_SAMPLE_TEAM_PK_SEQ_GENERATOR",
            sequenceName = "TB_SAMPLE_TEAM_PK_SEQ", // 실제 시퀀스 테이블명
            initialValue = 1,
            allocationSize = 50
    )
    private Long id;

    /**
     * 샘플 이름
     */
    @Column(name = "SAMPLE_TEAM_NM", length = 100)
    private String sampleTeamNm;

    /**
     * 샘플 설명
     */
    @Column(name = "SAMPLE_TEAM_DESC", length = 200)
    private String sampleTeamDesc;

    /**
     * UID 는 고유 값을 비교하기 위함
     */
    @Column(name = "SAMPLE_TEAM_UID", length = 32, unique = true, nullable = false, updatable = false)
    private String uid;

    /**
     * UID 생성 함수
     */
    @PrePersist
    public void createUid() {
        this.uid = GeneratorKeyValue.generateUidValue();
    }


}
