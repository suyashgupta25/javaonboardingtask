package de.appsfactory.customerservice.customer;

import de.appsfactory.customerservice.util.DummyObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByName_thenReturnCustomer() {
        Customer obj = DummyObjects.getCustomer();
        entityManager.persist(obj);
        entityManager.flush();

        Customer found = customerRepository.findByEmail(obj.getEmail());

        assertThat(found.getEmail())
                .isEqualTo(obj.getEmail());
    }
}
