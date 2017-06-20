package com.kaluzny.repository;

import com.kaluzny.domain.User;
import com.kaluzny.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Inject
    private UserRepository repository;
    @Inject
    private TestEntityManager entityManager;

    @Test
    public void findByTitle_CorrectString_Success() {
        entityManager.merge(new User(1, "test title", 20));
        List<User> list = this.repository.findByTitle("test title");
        list.stream().forEach(x -> System.out.println(x.getTitle()));
        assertEquals(list.size(), 1);
    }
}
