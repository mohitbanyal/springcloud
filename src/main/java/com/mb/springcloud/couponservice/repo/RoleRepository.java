package com.mb.springcloud.couponservice.repo;

import com.mb.springcloud.couponservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
