package server.crm.utils.log;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.crm.CrmApplication;

/**
 * @author: TungPham
 * @create: 28/06/2018
 */
@Service
public class LoggerImpl implements Logger {


    @Override
    public ILog with(LogType type) {
        return new LogBuilder(type);
    }

    public static class LogBuilder implements ILog {

        private org.slf4j.Logger logger;

        LogBuilder(LogType type) {
            logger = LoggerFactory.getLogger(type.getValue());
        }

        @Override
        public void info(String mess) {
            if (CrmApplication.IS_DEBUG()) {
                System.out.println(mess);
            }
            if (logger != null) {
                logger.info(mess);
            }
        }

        @Override
        public void debug(String mess) {
            if (CrmApplication.IS_DEBUG()) {
                System.out.println(mess);
            }
            if (logger != null) {
                logger.debug(mess);
            }
        }

        @Override
        public void warn(String mess) {
            if (CrmApplication.IS_DEBUG()) {
                System.out.println(mess);
            }
            if (logger != null) {
                logger.warn(mess);
            }
        }

        @Override
        public void error(String mess) {
            if (CrmApplication.IS_DEBUG()) {
                System.out.println(mess);
            }
            if (logger != null) {
                logger.error(mess);
            }
        }
    }
}
