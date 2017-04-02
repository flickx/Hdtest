package com.flickx.web.index.service.impl;

import com.flickx.web.index.service.IndexServ;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author flick on 2017/4/1.
 * @version 1.0
 */
@Service()
public class IndexServImpl implements IndexServ {

    public String get(String id) {
        return "It is "+id+" get!";
    }

    public String del(String id) {
        return "It is "+id+" del!";
    }

    public String save(String id) {
        return "It is "+id+" save!";
    }

    public List<?> list() {
        List<String> list = Arrays.asList("id:1","id:2","id:3","id:4");
        return list;
    }
}
