package com.lzx.hsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger log = LoggerFactory.getLogger(getClass());

   /* @Autowired*/
   /* private TestServiceImpl testServiceImpl;*/

   /* @RequestMapping(value = "/")
    public List<News> home() {
        log.info(testServiceImpl.selectAll().toString());
        return testServiceImpl.selectAll();
    }*/
}
