package ngo.drc.address.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.batch.batchInitV1.hromada.HromadaData;
import ngo.drc.address.dto.HromadaResponseDto;
import ngo.drc.address.entity.Hromada;
import ngo.drc.address.mapper.HromadaMapper;
import ngo.drc.address.repository.HromadaRepository;
import ngo.drc.locale.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HromadaService {
    private final HromadaRepository hromadaRepository;
    private final HromadaMapper hromadaMapper;
    @Transactional
    public void saveHromada(HromadaData hromadaData){
        Hromada hromada = hromadaMapper.toEntity(hromadaData);
        hromadaRepository.save(hromada);
    }

    @Transactional
    public void saveHromadas(List<HromadaData> hromadaData) {
        List<Hromada> list = hromadaData.stream().map(hromadaMapper::toEntity).toList();
        hromadaRepository.saveAll(list);
    }

    @Transactional(readOnly = true)
    public List<HromadaResponseDto> getHromadasByDistrictCode(String districtCode) {
        return switch (Locale.forLanguageTag(LocaleContextHolder.getLocale()).getLanguage()) {
            case "uk" -> hromadaRepository.findAllUaByDistrictCode(districtCode);
            default -> hromadaRepository.findAllEnByDistrictCode(districtCode);
        };
    }
}
