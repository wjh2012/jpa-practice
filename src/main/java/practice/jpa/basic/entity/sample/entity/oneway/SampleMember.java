package practice.jpa.basic.entity.sample.entity.oneway;

import lombok.*;
import org.hibernate.annotations.Comment;
import practice.jpa.basic.entity.sample.BaseEntity;
import practice.jpa.basic.entity.sample.GeneratorKeyValue;

import javax.persistence.*;

@Entity
@Table(name = "TB_SAMPLE_MEMBER") // 실제 데이터베이스 테이블명
@Builder(toBuilder = true) // 작동 안할 시 내부에 작성
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(callSuper = true)
public class SampleMember extends BaseEntity {

    /**
     * ID
     */
    @Id
    @Column(name = "SAMPLE_MEMBER_ID", nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TB_SAMPLE_MEMBER_PK_SEQ_GENERATOR")
    @SequenceGenerator(
            name = "TB_SAMPLE_MEMBER_PK_SEQ_GENERATOR",
            sequenceName = "TB_SAMPLE_MEMBER_PK_SEQ", // 실제 시퀀스 테이블명
            initialValue = 1,
            allocationSize = 50
    )
    private Long id;

    /**
     * 본인 DB와 동일하게
     * Column  지정된 제약조건이 ddl-auto 테이블 생성 시 자동으로 적용됩니다.
     * length, unique, nullable 등
     */
    @Column(name = "SAMPLE_MEMBER_NM", length = 100)
    private String sampleMemberNm;

    /**
     * 샘플 멤버 설명
     */
    @Column(name = "SAMPLE_MEMBER_DESC", length = 200)
    @Comment("db 코멘트 테스트")
    private String sampleMemberDesc;

    /**
     * N:1 단방향
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SAMPLE_TEAM_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    // 조인할 상대의 실제 테이블 컬럼명 // 실제 DB의 외래키는 설정하지 않음
    private SampleTeam sampleTeam;


    /**
     * UID 는 고유 값을 비교하기 위함
     */
    @Column(name = "SAMPLE_MEMBER_UID", length = 32, unique = true, nullable = false, updatable = false)
    private String uid;

    /**
     * UID 생성 함수
     */
    @PrePersist
    public void createUid() {
        this.uid = GeneratorKeyValue.generateUidValue();
    }

}
