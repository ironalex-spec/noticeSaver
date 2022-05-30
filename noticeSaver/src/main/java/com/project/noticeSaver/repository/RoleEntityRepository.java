package com.project.noticeSaver.repository;

import com.project.noticeSaver.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(String name);
}
