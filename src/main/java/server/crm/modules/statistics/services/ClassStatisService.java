package server.crm.modules.statistics.services;

import java.util.Map;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
public interface ClassStatisService {
    Map<String, Object> getClassStatisticNumbers(String year);

    Map<String, Object> getClassStatisticNumbersByYear(String year);
}
