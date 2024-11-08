package az.myecommerceapp.repository;

import az.myecommerceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String mail);

    User findByUserName(String userName);

    User findByEmail(String email);
}
