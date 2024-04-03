package practice.jpa.basic.query;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberQuery {

    @PersistenceContext
    private EntityManager em;
}
