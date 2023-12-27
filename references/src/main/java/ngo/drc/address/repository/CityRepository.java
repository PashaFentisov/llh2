package ngo.drc.address.repository;

import ngo.drc.address.dto.CityResponseDto;
import ngo.drc.address.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    @Query("SELECT new ngo.drc.address.dto.CityResponseDto(c.id, c.code, c.hromadaCode, c.nameEn) FROM City c where c.hromadaCode = :hromadaCode")
    List<CityResponseDto> findAllEnByHromadaCode(@Param("hromadaCode") String hromadaCode);

    @Query("SELECT new ngo.drc.address.dto.CityResponseDto(c.id, c.code, c.hromadaCode, c.nameUa) FROM City c where c.hromadaCode = :hromadaCode")
    List<CityResponseDto> findAllUaByHromadaCode(@Param("hromadaCode") String hromadaCode);
}
