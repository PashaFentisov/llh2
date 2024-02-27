package ngo.drc.core.currency.repository;


import ngo.drc.core.currency.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, UUID> {
    @Query("SELECT cr FROM CurrencyRate cr ORDER BY cr.creationDate DESC LIMIT 1")
    Optional<CurrencyRate> findLastCurrencyRate();

    @Query("SELECT cr FROM CurrencyRate cr WHERE cr.creationDate <= :date ORDER BY cr.creationDate DESC LIMIT 1")
    Optional<CurrencyRate> findLastByDateCurrencyRate(@Param("date") OffsetDateTime date);
}

