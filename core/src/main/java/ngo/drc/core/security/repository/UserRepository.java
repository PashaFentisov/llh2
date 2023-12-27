package ngo.drc.core.security.repository;

import ngo.drc.core.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u left join fetch u.role WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    @Query("UPDATE User u SET u.isDeleted = true WHERE u.id = :id")
    @Modifying
    void setUserAsDeleted(@Param("id") UUID id);
}
