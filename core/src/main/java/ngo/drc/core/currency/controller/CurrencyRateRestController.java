package ngo.drc.core.currency.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ngo.drc.core.currency.dto.CurrencyRateResponseDto;
import ngo.drc.core.currency.dto.CurrencyRateSavingDto;
import ngo.drc.core.currency.service.CurrencyRateService;
import ngo.drc.core.endpoint.PageResponse;
import ngo.drc.core.exception.BigSizeException;
import ngo.drc.core.exception.EntityValidationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/currency-rate")
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateRestController {
    private final CurrencyRateService currencyRateService;

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRateResponseDto> getCurrencyRateById(@PathVariable UUID id) {
        return ResponseEntity.ok(currencyRateService.getCurrencyRateById(id));
    }

    @GetMapping()
    public ResponseEntity<PageResponse<CurrencyRateResponseDto>> getCurrencyRates(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {

        if (size > 100) {
            throw new BigSizeException("You can get maximum 100 currency rates at one time");
        }
        PageResponse<CurrencyRateResponseDto> currencyRates = currencyRateService.getCurrencyRates(PageRequest.of(page, size, Sort.by(sort)));
        return ResponseEntity.ok(currencyRates);
    }

    @PostMapping
    public ResponseEntity<CurrencyRateResponseDto> saveCurrencyRate(@RequestBody @Valid CurrencyRateSavingDto currencyRateSavingDto,
                                                                    Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> log.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        CurrencyRateResponseDto currencyRateResponseDto = currencyRateService.saveCurrencyRate(currencyRateSavingDto);
        return ResponseEntity.ok(currencyRateResponseDto);
    }
}
