package ngo.drc.address.batch.batchInitV1.hromada;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.service.HromadaService;
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
        List<HromadaData> hromadas = (List<HromadaData>) chunk.getItems();
        hromadaService.saveHromadas(hromadas);
    }
}
