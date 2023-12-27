package ngo.drc.micro.repository;

import ngo.drc.micro.entity.MainForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MainFormRepository extends JpaRepository<MainForm, UUID> {
    @Query("SELECT m FROM MainForm m WHERE m.isDeleted = false")
    Page<MainForm> findAllNotDeleted(Pageable pageable);
}
