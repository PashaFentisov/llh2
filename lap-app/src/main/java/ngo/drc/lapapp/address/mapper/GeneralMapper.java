package ngo.drc.lapapp.address.mapper;

public interface GeneralMapper <T, E>{
    T toDto(E entity);
    E toEntity(T dto);
}
