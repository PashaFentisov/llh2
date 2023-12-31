package ngo.drc.micro.repository;

import ngo.drc.micro.entity.MainFormLastVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface MainFormLastVersionRepository extends JpaRepository<MainFormLastVersion, UUID> {

    @Query("SELECT m FROM MainFormLastVersion m WHERE m.mainFormId = :mainFormId")
    Optional<MainFormLastVersion> findByMainFormId(@Param("mainFormId") UUID mainFormId);
}
