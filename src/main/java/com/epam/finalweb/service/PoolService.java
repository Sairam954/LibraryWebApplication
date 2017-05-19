package com.epam.finalweb.service;

import com.epam.finalweb.exception.ServiceException;

public interface PoolService {

	void poolIntialise() throws ServiceException;
	void detroyPool() ;
}
