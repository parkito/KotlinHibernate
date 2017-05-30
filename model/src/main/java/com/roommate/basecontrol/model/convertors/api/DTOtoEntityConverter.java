package com.roommate.basecontrol.model.convertors.api;

import java.util.List;

/**
 * @author Artem Karnov @date 25.01.2017.
 *         artem.karnov@t-systems.com
 **/
public interface DTOtoEntityConverter<D, E> {
    E convert(D dto);

    List<E> convertList(List<D> dtoList);
}
