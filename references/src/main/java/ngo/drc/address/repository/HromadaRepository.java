package ngo.drc.address.repository;

import ngo.drc.address.dto.HromadaResponseDto;
import ngo.drc.address.entity.Hromada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HromadaRepository extends JpaRepository<Hromada, Long> {
    @Query("SELECT new ngo.drc.address.dto.HromadaResponseDto(h.id, h.code, h.districtCode, h.nameEn) FROM Hromada h where h.districtCode = :districtCode")
    List<HromadaResponseDto> findAllEnByDistrictCode(@Param("districtCode") String districtCode);

    @Query("SELECT new ngo.drc.address.dto.HromadaResponseDto(h.id, h.code, h.districtCode, h.nameUa) FROM Hromada h where h.districtCode = :districtCode")
    List<HromadaResponseDto> findAllUaByDistrictCode(@Param("districtCode") String districtCode);
}
