package ngo.drc.address.batch.batchInitV1.district;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.service.DistrictService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DistrictItemWriter implements ItemWriter<DistrictData> {
    private final DistrictService districtService;

    @Override
    public void write(Chunk<? extends DistrictData> chunk) {
        List<DistrictData> districts = (List<DistrictData>) chunk.getItems();
        districtService.saveDistricts(districts);
    }
}
