package ngo.drc.micro.repository;

import ngo.drc.micro.entity.ApplicationFormMicro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ApplicationFormMicroRepository extends JpaRepository<ApplicationFormMicro, UUID> {
    @Query("SELECT m FROM ApplicationFormMicro m WHERE m.isDeleted = false")
    Page<ApplicationFormMicro> findAllNotDeleted(Pageable pageable);
}
