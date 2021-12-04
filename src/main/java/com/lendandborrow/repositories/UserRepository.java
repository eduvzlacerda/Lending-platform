package com.lendandborrow.repositories;

import com.lendandborrow.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(
            value = "SELECT * FROM USERS u WHERE u.email LIKE ?",
            nativeQuery = true
    )
    User findByEmail(String email);
}
