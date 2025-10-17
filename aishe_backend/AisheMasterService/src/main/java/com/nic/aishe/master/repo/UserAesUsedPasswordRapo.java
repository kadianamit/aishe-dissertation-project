package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.UserAesUsedPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserAesUsedPasswordRapo extends JpaRepository<UserAesUsedPassword, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM user_aes_password " +
            "WHERE token = :token " +
            "AND date_time <= :dateTime " +
            "AND is_token_expired = true", nativeQuery = true)
    Boolean fetchTokenNotExpired(@Param("token") String token, @Param("dateTime") LocalDateTime dateTime);
}
