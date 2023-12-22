package ngo.drc.endpoint.mapper;

import ngo.drc.endpoint.PageResponse;
import org.springframework.data.domain.Page;

public interface PageMapper {
    <T> PageResponse<T> toPageResponse(Page<T> page);
}
