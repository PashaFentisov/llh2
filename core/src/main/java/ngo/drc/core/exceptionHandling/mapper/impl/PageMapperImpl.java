package ngo.drc.core.exceptionHandling.mapper.impl;


import ngo.drc.core.exceptionHandling.PageResponse;
import ngo.drc.core.exceptionHandling.mapper.PageMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageMapperImpl implements PageMapper {
    @Override
    public <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page);
    }
}
