package cn.sixlab.mine.dao.service;

import cn.sixlab.mine.dao.bean.Col;
import cn.sixlab.mine.dao.dao.TableMapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    // @Autowired
    // private Params params;
    // public String tableName(String table) {
    //     if (params.getAlias().containsKey(table)) {
    //         return params.getAlias().get(table);
    //     }
    //     return table;
    // }

    @Autowired
    private TableMapper mapper;

    public boolean checkTable(String tableName) {
        String check = mapper.check(tableName);

        System.out.println(check);

        return StringUtils.isNotEmpty(check);
    }

    public void createTable(String tableName, Map<String, Object> data) {
        List<Col> cols = (List<Col>) data.get("cols");
        mapper.create(tableName, cols);
    }

    public void dropTable(String tableName) {
        mapper.drop(tableName);
    }

    public void add(String tableName, Map<String, Object> data) {
        List<Col> cols = (List<Col>) data.get("cols");
        mapper.add(tableName, cols);
    }

    public Map del(String tableName, Map<String, Object> data) {
        Integer id = MapUtils.getInteger(data, "id");
        Map map = mapper.selectById(tableName, id);

        mapper.del(tableName, id);

        return map;
    }

    public Map update(String tableName, Map<String, Object> data) {
        Integer id = MapUtils.getInteger(data, "id");
        List<Col> cols = (List<Col>) data.get("cols");
        mapper.update(tableName, id, cols);

        return mapper.selectById(tableName, id);
    }

    public List query(String tableName, Map<String, Object> data) {
        List<String> cols = (List<String>) data.get("cols");
        List<String> cnds = (List<String>) data.get("cnds");
        String orders = MapUtils.getString(data, "orders");
        List result = mapper.query(tableName, cols, cnds, orders);
        return result;
    }
}
