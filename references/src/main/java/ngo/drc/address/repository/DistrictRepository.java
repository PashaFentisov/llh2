package ngo.drc.address.repository;

import ngo.drc.address.dto.DistrictResponseDto;
import ngo.drc.address.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("SELECT new ngo.drc.address.dto.DistrictResponseDto(d.id, d.code, d.regionCode, d.nameEn) FROM District d where d.regionCode = :regionCode")
    List<DistrictResponseDto> findAllEnByRegionCode(@Param("regionCode") String regionCode);

    @Query("SELECT new ngo.drc.address.dto.DistrictResponseDto(d.id, d.code, d.regionCode, d.nameUa) FROM District d where d.regionCode = :regionCode")
    List<DistrictResponseDto> findAllUaByRegionCode(@Param("regionCode") String regionCode);
}
