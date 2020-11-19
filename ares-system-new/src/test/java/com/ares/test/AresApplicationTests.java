package com.ares.test;


import com.alibaba.fastjson.JSON;
import com.ares.AresSystemApplication;
import com.ares.message.dao.AresDocumentRepository;
import com.ares.message.model.AresDocument;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AresSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AresApplicationTests {

    @Resource
    AresDocumentRepository repository;


    @Test
    void contextLoads() {
    }

    @Test
    public void testES() {

        AresDocument demo = new AresDocument("1", "ares-name", "ares-key", "this is a test content.", "this is a test body!");
        repository.save(demo);

        System.out.println("==================================");
        Pageable pageable = PageRequest.of(0, 10);
        Page<AresDocument> page = repository.findByContentIsContaining("content", pageable);
        System.out.println("search content:" + JSON.toJSON(page.getContent()));
        System.out.println("==================================");
        System.out.println("all:" + JSON.toJSON(repository.findAll()));
    }
}
