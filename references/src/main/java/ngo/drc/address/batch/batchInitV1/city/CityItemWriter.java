package ngo.drc.address.batch.batchInitV1.city;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.service.CityService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("cityItemWriter")
@RequiredArgsConstructor
public class CityItemWriter implements ItemWriter<CityData> {
    private final CityService cityService;
    @Override
    public void write(Chunk<? extends CityData> chunk){
        List<CityData> cities = (List<CityData>) chunk.getItems();
        cityService.saveCities(cities);
    }
}
