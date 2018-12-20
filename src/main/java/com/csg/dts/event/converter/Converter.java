package com.csg.dts.event.converter;

import com.csg.dts.execption.ApplicationException;

@FunctionalInterface
public interface Converter<T, E> {
	
	E convert(T t) throws ApplicationException;

}
