package com.maxim.bokarev.test3.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Converter<E, D> {

	D toData(E entity);

	E toEntity(D data);
	
	default Collection<D> toDataCollection(Iterable<E> entities) {
		List<D> data = new ArrayList<>();
		for(E entity: entities) {
			data.add(this.toData(entity));
		}
		return data;
	}

}
