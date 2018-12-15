package server.crm.modules.statistics.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassStatisticServiceImplTest {

    @Autowired
    CourseStatisticServiceImpl courseStatisticService;
    @Test
    public void getTest() {
        //List list = courseStatisticService.getReverse("2018");
        //System.out.println(list.size());
    }
}