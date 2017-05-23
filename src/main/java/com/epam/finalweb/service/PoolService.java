package com.epam.finalweb.service;

import com.epam.finalweb.service.exception.ServiceException;

public interface PoolService {

	void poolIntialise() throws ServiceException;
	void detroyPool() ;
}
