package cn.sixlab.mine.dao.utils;

import lombok.Data;

import java.util.Map;

@Data
public class JsonParam {
    private OpType opType;
    private String tableName;
    private Map<String, Object> data;
}
