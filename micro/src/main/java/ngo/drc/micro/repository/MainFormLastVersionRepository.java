package ngo.drc.micro.repository;

import ngo.drc.micro.entity.MainFormLastVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MainFormLastVersionRepository extends JpaRepository<MainFormLastVersion, Long> {

    @Query("SELECT m FROM MainFormLastVersion m WHERE m.userId = :userId")
    Optional<MainFormLastVersion> findByUserId(@Param("userId") Long userId);
}
