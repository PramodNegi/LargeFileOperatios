package com.csg.dts.event.processor;

import com.csg.dts.execption.ApplicationException;

@FunctionalInterface
public interface Processor<T> {
	
	void process(T t) throws ApplicationException;
}
