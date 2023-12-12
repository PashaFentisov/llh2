package ngo.drc.lapapp.address.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.lapapp.address.batch.batchInitV1.hromada.HromadaData;
import ngo.drc.lapapp.address.entity.Hromada;
import ngo.drc.lapapp.address.mapper.HromadaMapper;
import ngo.drc.lapapp.address.repository.HromadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
