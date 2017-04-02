package com.flickx.web.index.service;

import java.util.List;

/**
 * @author flick on 2017/4/1.
 * @version 1.0
 */
public interface IndexServ {
    String get(String id);
    String del(String id);
    String save(String id);
    List<?> list();
}
