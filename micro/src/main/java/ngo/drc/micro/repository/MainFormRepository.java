package ngo.drc.micro.repository;

import ngo.drc.micro.entity.MainForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MainFormRepository extends JpaRepository<MainForm, Long> {
    @Query("SELECT m FROM MainForm m WHERE m.isDeleted = false")
    Page<MainForm> findAllNotDeleted(Pageable pageable);
}
