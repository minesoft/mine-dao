package cn.sixlab.mine.dao.dao;

import cn.sixlab.mine.dao.bean.Col;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TableMapper {

    String check(@Param("tableName") String tableName);

    void create(@Param("tableName") String tableName, @Param("cols") List<Col> cols);

    void drop(@Param("tableName") String tableName);

    void add(@Param("tableName") String tableName, @Param("cols") List<Col> cols);

    void del(@Param("tableName") String tableName, @Param("id") Integer id);

    void update(@Param("tableName") String tableName, @Param("id") Integer id, @Param("cols") List<Col> cols);

    Map selectById(@Param("tableName") String tableName, @Param("id") Integer id);

    List<Map> query(@Param("tableName") String tableName,
                    @Param("cols") List<String> cols, @Param("cnds") List<String> cnds,
                    @Param("orders") String orders);
}
