package server.crm.utils.log;
/**
 * @author: TungPham
 * @create: 18/08/2018
 */
public enum LogType {
    SMS("sms-log"), SERVER("server-log");
    private String value;

    LogType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public LogType findValue(String value) {
        for (LogType i : values()) {
            if (i.getValue().equals(value)) {
                return i;
            }
        }
        return SERVER;
    }
}
