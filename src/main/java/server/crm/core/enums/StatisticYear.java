package server.crm.core.enums;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
public enum StatisticYear {
    IN_YEAR("inYear"),
    IN_QUARTER_1("quarter1"),
    IN_QUARTER_2("quarter2"),
    IN_QUARTER_3("quarter3"),
    IN_QUARTER_4("quarter4");
    private String code;

    StatisticYear(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
