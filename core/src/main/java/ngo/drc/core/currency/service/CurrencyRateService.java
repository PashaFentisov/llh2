package ngo.drc.core.currency.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ngo.drc.core.currency.dto.CurrencyRateByDateRequestDto;
import ngo.drc.core.currency.dto.CurrencyRateResponseDto;
import ngo.drc.core.currency.dto.CurrencyRateSavingDto;
import ngo.drc.core.currency.entity.CurrencyRate;
import ngo.drc.core.currency.mapper.CurrencyRateResponseMapper;
import ngo.drc.core.currency.mapper.CurrencyRateSavingMapper;
import ngo.drc.core.currency.repository.CurrencyRateRepository;
import ngo.drc.core.exceptionHandling.PageResponse;
import ngo.drc.core.exceptionHandling.mapper.PageMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    private final CurrencyRateRepository currencyRateRepository;
    private final PageMapper pageMapper;
    private final CurrencyRateResponseMapper currencyRateResponseMapper;
    private final CurrencyRateSavingMapper currencyRateSavingMapper;

    @Transactional(readOnly = true)
    public PageResponse<CurrencyRateResponseDto> getCurrencyRates(Pageable pageable) {
        return pageMapper.toPageResponse(currencyRateRepository.findAll(pageable).map(currencyRateResponseMapper::toDto));
    }

    @Transactional
    public CurrencyRateResponseDto saveCurrencyRate(CurrencyRateSavingDto currencyRateSavingDto) {
        CurrencyRate currencyRate = currencyRateSavingMapper.toEntity(currencyRateSavingDto);
        return currencyRateResponseMapper.toDto(currencyRateRepository.save(currencyRate));
    }

    @Transactional(readOnly = true)
    public CurrencyRateResponseDto getCurrencyRateById(UUID id) {
        CurrencyRate currencyRate = currencyRateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currency rate with id " + id + " does not exist"));
        return currencyRateResponseMapper.toDto(currencyRate);
    }

    @Transactional(readOnly = true)
    public CurrencyRateResponseDto getLastCurrencyRateBy() {
        CurrencyRate currencyRate = currencyRateRepository.findLastCurrencyRate()
                .orElseThrow(() -> new EntityNotFoundException("Currency rate does not exist"));
        return currencyRateResponseMapper.toDto(currencyRate);
    }

    @Transactional(readOnly = true)
    public CurrencyRateResponseDto getLastByDateCurrencyRate(CurrencyRateByDateRequestDto currencyRateByDateRequestDto) {
        CurrencyRate currencyRate = currencyRateRepository.findLastByDateCurrencyRate(currencyRateByDateRequestDto.getDate())
                .orElseThrow(() -> new EntityNotFoundException("Currency rate does not exist"));
        return currencyRateResponseMapper.toDto(currencyRate);
    }
}
