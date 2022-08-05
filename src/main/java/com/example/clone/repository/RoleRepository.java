package com.example.clone.repository;


import com.example.clone.entity.Role;
import com.example.clone.util.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /*
     * Find role by name
     * @param name
     * return role
     * */
    Optional<Role> findByName(Enums.RoleSecurity name);
}
