package server.crm.utils.log;

public interface ILog {
    void info(String mess);

    void debug(String mess);

    void warn(String mess);

    void error(String mess);
}
