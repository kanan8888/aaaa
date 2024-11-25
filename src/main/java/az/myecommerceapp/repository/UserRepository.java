package az.myecommerceapp.repository;

import az.myecommerceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String mail);

    User findByUserNameAndPassword(String userName, String password);

    User findByEmail(String email);
}
