package server.crm.modules.statistics.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.crm.core.enums.StatisticYear;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.statistics.repositories.ClassStatisticsRepository;
import server.crm.modules.statistics.services.ClassStatisService;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
@Service
public class ClassStatisticServiceImpl implements ClassStatisService {
    @Autowired
    private ClassStatisticsRepository classRepository;

    @Override
    public Map<String, Object> getClassStatisticNumbers(String year) {
        try {
            int yearNum = Integer.valueOf(year);
            LocalDate currentYear = LocalDate.of(yearNum, Month.DECEMBER, 31);
            LocalDate lastYear = currentYear.minusYears(1);
            Map<String, Object> statisticMap = new HashMap<>();

            Map<String, Object> statisticMapOpenedClasses = new HashMap<>();
            statisticMapOpenedClasses.put("lastYear", this.getClassStatisticsInYear(lastYear));
            statisticMapOpenedClasses.put("thisYear", this.getClassStatisticsInYear(currentYear));
            Map<String, Object> statisticMapEndedClasses = new HashMap<>();
            statisticMapEndedClasses.put("lastYear", this.getClassesEndInYear(lastYear));
            statisticMapEndedClasses.put("thisYear", this.getClassesEndInYear(currentYear));

            statisticMap.put("opened", statisticMapOpenedClasses);
            statisticMap.put("ended", statisticMapEndedClasses);

            return statisticMap;
        } catch (NumberFormatException e)
        {
            throw new ApiRuntimeException("Error throw when convert year!");
        }
    }

    @Override
    public Map<String, Object> getClassStatisticNumbersByYear(String year) {
        LocalDate yearDate = LocalDate.now();
        try {
            Integer yearInt = Integer.valueOf(year);
            yearDate = LocalDate.of(yearInt, Month.JANUARY, 1);
        } catch (Exception e) {
            throw new ApiRuntimeException("Parse year throws an excaption!");
        }
        Map<String, Object> statisticMap = new HashMap<>();
        statisticMap.put("opened", this.getClassStatisticsInYear(yearDate));
        statisticMap.put("ended", this.getClassesEndInYear(yearDate));

        return statisticMap;

    }


    private Map<String, Object> getClassStatisticsInYear(LocalDate year) {
        Map<String, Object> map = new HashMap<>();
        int numberOfClassesInYear = classRepository.getNumberOfClassFromSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JANUARY, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.DECEMBER, 31)));
        int numberOfClassesInQuarter1Year = classRepository.getNumberOfClassFromSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JANUARY, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.MARCH, 31)));
        int numberOfClassesInQuarter2Year = classRepository.getNumberOfClassFromSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.APRIL, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JUNE, 30)));
        int numberOfClassesInQuarter3Year = classRepository.getNumberOfClassFromSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JULY, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.SEPTEMBER, 30)));
        int numberOfClassesInQuarter4Year = classRepository.getNumberOfClassFromSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.OCTOBER, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.DECEMBER, 31)));

        map.put(StatisticYear.IN_YEAR.getCode(), numberOfClassesInYear);
        map.put(StatisticYear.IN_QUARTER_1.getCode(), numberOfClassesInQuarter1Year);
        map.put(StatisticYear.IN_QUARTER_2.getCode(), numberOfClassesInQuarter2Year);
        map.put(StatisticYear.IN_QUARTER_3.getCode(), numberOfClassesInQuarter3Year);
        map.put(StatisticYear.IN_QUARTER_4.getCode(), numberOfClassesInQuarter4Year);
        return map;
    }

    private Map<String, Object> getClassesEndInYear(LocalDate year) {
        LocalDate startDate = LocalDate.of(year.getYear(), Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(year.getYear(), Month.DECEMBER, 31);
        Map<String, Object> map = new HashMap<>();
        if (LocalDate.now().getYear() <= year.getYear()) {
            endDate = LocalDate.now();
        }
        int numberOfClassesEndedInYear = classRepository.getNumberOfClassEndInSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JANUARY, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.DECEMBER, 31)));
        int numberOfClassesEndedInQuarter1Year = classRepository.getNumberOfClassEndInSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JANUARY, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.MARCH, 31)));
        int numberOfClassesEndedInQuarter2Year = classRepository.getNumberOfClassEndInSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.APRIL, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JUNE, 30)));
        int numberOfClassesEndedInQuarter3Year = classRepository.getNumberOfClassEndInSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.JULY, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.SEPTEMBER, 30)));
        int numberOfClassesEndedInQuarter4Year = classRepository.getNumberOfClassEndInSpecificDate(
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.OCTOBER, 1)),
                java.sql.Date.valueOf(LocalDate.of(year.getYear(), Month.DECEMBER, 31)));

        map.put(StatisticYear.IN_YEAR.getCode(), numberOfClassesEndedInYear);
        map.put(StatisticYear.IN_QUARTER_1.getCode(), numberOfClassesEndedInQuarter1Year);
        map.put(StatisticYear.IN_QUARTER_2.getCode(), numberOfClassesEndedInQuarter2Year);
        map.put(StatisticYear.IN_QUARTER_3.getCode(), numberOfClassesEndedInQuarter3Year);
        map.put(StatisticYear.IN_QUARTER_4.getCode(), numberOfClassesEndedInQuarter4Year);

        return map;
    }

}
