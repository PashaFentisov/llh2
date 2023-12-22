package ngo.drc.endpoint.mapper.impl;


import ngo.drc.endpoint.PageResponse;
import ngo.drc.endpoint.mapper.PageMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageMapperImpl implements PageMapper {
    @Override
    public <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page);
    }
}
