package ngo.drc.references.address.batch.batchInitV1.hromada;

import lombok.RequiredArgsConstructor;
import ngo.drc.lapapp.address.batch.batchInitV1.city.CityData;
import ngo.drc.lapapp.address.service.CityService;
import ngo.drc.lapapp.address.service.HromadaService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HromadaItemWriter implements ItemWriter<HromadaData> {
    private final HromadaService hromadaService;
    @Override
    public void write(Chunk<? extends HromadaData> chunk){
        List<? extends HromadaData> hromades = chunk.getItems();
        for (HromadaData hromada : hromades) {
            hromadaService.saveHromada(hromada);
        }
    }
}
