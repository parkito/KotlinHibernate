package com.roommate.basecontrol.model.convertors.api;

import java.util.List;

/**
 * @author Artem Karnov @date 25.01.2017.
 *         artem.karnov@t-systems.com
 **/
public interface EntityToDTOConverter<E, D> {
    D convert(E entity);

    List<D> convertList(List<E> entitiesList);
}
