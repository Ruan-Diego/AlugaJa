package com.alu.ufc.AlugaJa.commons;

import java.util.List;
import java.util.Map;

public interface GenericServiceAPI<I,O> {

    String update(I entity, String id) throws Exception;

    String save(I entity) throws Exception;

    void delete(String id) throws Exception;

    O get(String id) throws Exception;

    Map<String, Object> getAsMap(String id) throws Exception;

    List<O> getAll() throws Exception;
}