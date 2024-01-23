package ngo.drc.core.currency.repository;


import ngo.drc.core.currency.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, UUID> {
}
