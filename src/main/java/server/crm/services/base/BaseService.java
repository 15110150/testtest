package server.crm.services.base;

import server.crm.utils.log.Logger;
import server.crm.utils.log.LoggerImpl;

public class BaseService {
    protected Logger LOGGER;

    public BaseService() {
        LOGGER = new LoggerImpl();
    }
}
