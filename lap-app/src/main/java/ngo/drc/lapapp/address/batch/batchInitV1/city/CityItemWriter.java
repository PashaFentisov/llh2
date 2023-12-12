package ngo.drc.lapapp.address.batch.batchInitV1.city;

import lombok.RequiredArgsConstructor;
import ngo.drc.lapapp.address.service.CityService;
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
        List<? extends CityData> cities = chunk.getItems();
        for (CityData city : cities) {
            cityService.saveCity(city);
        }
    }
}
