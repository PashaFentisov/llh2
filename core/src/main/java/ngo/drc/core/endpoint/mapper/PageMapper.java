package ngo.drc.core.endpoint.mapper;

import ngo.drc.core.endpoint.PageResponse;
import org.springframework.data.domain.Page;

public interface PageMapper {
    <T> PageResponse<T> toPageResponse(Page<T> page);
}
