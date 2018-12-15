package server.crm.modules.statistics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.crm.modules.statistics.services.ClassStatisService;

import java.util.Map;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
@RestController
@RequestMapping("/api/v1.0/statistic")
public class ClassStatisticsController {
    @Autowired
    private ClassStatisService classService;

    @GetMapping(value = "/class", params = {"year"})
    public Map<String, Object> getClassStatistic(@RequestParam("year") String year) {
        return classService.getClassStatisticNumbers(year);
    }


//    @GetMapping(value = "/class", params = {"year"})
//    public Map<String, Object> getClassStatisticByYear(@RequestParam("year") String year) {
//        return classService.getClassStatisticNumbersByYear(year);
//    }
}
