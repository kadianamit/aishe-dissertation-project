package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefUserRoleMaster;
import com.nic.aishe.master.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefUserRoleMasterRepo extends JpaRepository<RefUserRoleMaster, Integer> {

    @Query(value = "select * from ref_user_management_role c  where c.role_id=:id AND c.level_id =:levelId ", nativeQuery = true)
    Optional<List<RefUserRoleMaster>> getUserRoleByIdandLevel(Integer id, Integer levelId);

    @Query(value = "select * from ref_user_management_role c  where c.level_id =:levelId ", nativeQuery = true)
    Optional<List<RefUserRoleMaster>> getUserRoleByLevel(Integer levelId);

    @Query(value = "select * from ref_user_management_role c  where c.role_id=:id", nativeQuery = true)
    Optional<List<RefUserRoleMaster>> getUserRoleById(Integer id);

    @Query(value = "select * from ref_user_management_role c  where c.role_id=:id", nativeQuery = true)
    RefUserRoleMaster getRoleById(Integer id);

    @Query(value = "select * from ref_user_management_role c  where c.approving_authority_role_profile is true ", nativeQuery = true)
    Optional<List<RefUserRoleMaster>>  getRoleByApprovingAuthority();
}
