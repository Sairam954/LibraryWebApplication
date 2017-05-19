package com.epam.finalweb.service.impl;

import com.epam.finalweb.dao.ConnectionPool;
import com.epam.finalweb.exception.PoolException;
import com.epam.finalweb.exception.ServiceException;
import com.epam.finalweb.service.PoolService;

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
