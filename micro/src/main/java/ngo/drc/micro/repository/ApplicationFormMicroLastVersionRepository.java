package ngo.drc.micro.repository;

import ngo.drc.micro.entity.ApplicationFormMicroLastVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationFormMicroLastVersionRepository extends JpaRepository<ApplicationFormMicroLastVersion, UUID> {

    @Query("SELECT m FROM ApplicationFormMicroLastVersion m WHERE m.applicationFormMicroId = :applicationFormMicroId")
    Optional<ApplicationFormMicroLastVersion> findByApplicationFormMicroId(@Param("applicationFormMicroId") UUID applicationFormMicroId);
}
