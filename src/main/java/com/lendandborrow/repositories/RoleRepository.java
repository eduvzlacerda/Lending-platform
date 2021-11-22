package com.lendandborrow.repositories;

import com.lendandborrow.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(
            value = "SELECT * FROM ROLES r WHERE r.name = ?1",
            nativeQuery = true
    )
    Role findRoleByName(String name);

}
