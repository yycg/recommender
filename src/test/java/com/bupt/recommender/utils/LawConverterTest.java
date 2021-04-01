package com.bupt.recommender.utils;

import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.service.LawService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:conf/spring-mvc.xml",
        "classpath:conf/spring-service.xml",
        "classpath:conf/mybatis-config.xml",
        "classpath:conf/spring-mybatis.xml",
        "classpath:conf/spring-shiro.xml"})
public class LawConverterTest {
    @Autowired
    private LawService lawService;

    @Test
    public void testLawRegularReplace() throws Exception {
        LawPO lawPO = lawService.getLawById("2c9081845404170501540440b91e3c02");
        LawConverter.regularReplace(lawPO);
        System.out.println(lawPO);
    }

    @Test
    public void testSentenceRegularReplace() throws Exception {
        String sentence = "topic:$word$topic:（医疗机构）法定代表人$word$topic:（姓名）不是临床用血管理第一责任人。";
        String result = LawConverter.regularReplace(sentence, "[$]word[$]topic:", "_______________");
        System.out.println(result);
    }
}
