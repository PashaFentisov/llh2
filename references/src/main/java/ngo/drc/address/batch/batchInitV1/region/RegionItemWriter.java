package ngo.drc.address.batch.batchInitV1.region;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.service.RegionService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("regionItemWriter")
@RequiredArgsConstructor
public class RegionItemWriter implements ItemWriter<RegionData> {
    private final RegionService regionService;

    @Override
    public void write(Chunk<? extends RegionData> chunk) {
        List<? extends RegionData> regions = chunk.getItems();
        for (RegionData region : regions) {
            regionService.saveRegion(region);
        }
    }
}
