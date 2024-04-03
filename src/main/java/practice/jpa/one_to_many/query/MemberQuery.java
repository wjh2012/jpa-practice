package practice.jpa.one_to_many.query;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberQuery {

    @PersistenceContext
    private EntityManager em;
}
