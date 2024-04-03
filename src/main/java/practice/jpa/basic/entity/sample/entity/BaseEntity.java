package practice.jpa.basic.entity.sample.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {


    private static final long serialVersionUID = 5224464905891155350L;
    /**
     * 최초 등록자
     */
    @CreatedBy
    @Column(name = "FST_REG_ID", length = 36, nullable = true, updatable = false)
    protected String fstRegId;

    /**
     * 최초 등록일시
     */
    @CreatedDate
    @Column(name = "FST_REG_DT", nullable = false, updatable = false)
    protected LocalDateTime fstRegDt;

    /**
     * 최종 변경자
     */
    @LastModifiedBy
    @Column(name = "LST_CHG_ID", length = 36, nullable = true, updatable = false)
    protected String lstChgId;

    /**
     * 최종 변경일시
     */
    @LastModifiedDate
    @Column(name = "LST_CHG_DT", nullable = false, updatable = false)
    protected LocalDateTime lstChgDt;

}
