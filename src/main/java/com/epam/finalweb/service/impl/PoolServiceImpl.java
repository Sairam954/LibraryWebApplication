package com.epam.finalweb.service.impl;

import com.epam.finalweb.dao.ConnectionPool;
import com.epam.finalweb.dao.exception.PoolException;
import com.epam.finalweb.service.PoolService;
import com.epam.finalweb.service.exception.ServiceException;

public class PoolServiceImpl implements PoolService {

	@Override
	public void poolIntialise() throws ServiceException {
		try {
			ConnectionPool.getInstance().init();
		} catch (PoolException e) {
			throw new ServiceException("Cannot intialise Pool",e);
		}
	}

	@Override
	public void detroyPool() {
		ConnectionPool.getInstance().destroy();
	}

}
