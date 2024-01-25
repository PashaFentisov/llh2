package ngo.drc.micro.repository;

import ngo.drc.micro.dto.ApplicationFormMicroStatsDto;
import ngo.drc.micro.entity.ApplicationFormMicro;
import ngo.drc.micro.enumeration.MicroStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ApplicationFormMicroRepository extends JpaRepository<ApplicationFormMicro, UUID> {
    @Query("SELECT m FROM ApplicationFormMicro m WHERE m.isDeleted = false")
    Page<ApplicationFormMicro> findAllNotDeleted(Pageable pageable);

    @Query("Select new ngo.drc.micro.dto.ApplicationFormMicroStatsDto(m.donor, m.status) from ApplicationFormMicro m where m.status in :statuses")
    List<ApplicationFormMicroStatsDto> getApplicationFormsInStatus(List<MicroStatus> statuses);
}
