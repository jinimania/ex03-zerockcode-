package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LeeSoohoon
 */
@RestController
@RequestMapping(value = "/sample")
public class SampleController {

    @RequestMapping(value = "/hello")
    public String sayHello() {

        return "Hello World ";
    }

    @RequestMapping(value = "/sendVO")
    public SampleVO sendVO() {

        final SampleVO vo = new SampleVO();
        vo.setFirstName("길동");
        vo.setLastName("홍");
        vo.setMno(123);

        return vo;
    }

    @RequestMapping(value = "/sendList")
    public List<SampleVO> sendList() {

        final ArrayList<SampleVO> list = new ArrayList<>();
        listAdd(list);
        return list;
    }

    @RequestMapping(value = "/sendMap")
    public Map<Integer, SampleVO> sendMap() {

        final Map<Integer, SampleVO> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            final SampleVO vo = new SampleVO();
            vo.setFirstName("길동");
            vo.setLastName("홍");
            vo.setMno(i);

            map.put(i, vo);
        }
        return map;
    }

    @RequestMapping(value = "/sendErrorAuth")
    public ResponseEntity<Void> sendListAuth() {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/sendErrorNot")
    public ResponseEntity<List<SampleVO>> sendListNot() {

        final ArrayList<SampleVO> list = new ArrayList<>();
        listAdd(list);

        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

    private void listAdd(final ArrayList<SampleVO> list) {
        for (int i = 0; i < 10; i++) {
            final SampleVO vo = new SampleVO();
            vo.setFirstName("길동");
            vo.setLastName("홍");
            vo.setMno(i);

            list.add(vo);
        }
    }
}
