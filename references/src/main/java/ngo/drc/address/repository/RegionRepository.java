package ngo.drc.address.repository;

import ngo.drc.address.dto.RegionResponseDto;
import ngo.drc.address.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("SELECT new ngo.drc.address.dto.RegionResponseDto(r.id, r.code, r.nameEn) FROM Region r")
    List<RegionResponseDto> findAllEn();

    @Query("SELECT new ngo.drc.address.dto.RegionResponseDto(r.id, r.code, r.nameUa) FROM Region r")
    List<RegionResponseDto> findAllUa();
}
