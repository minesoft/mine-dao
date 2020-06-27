package cn.sixlab.mine.dao.controller;

import cn.sixlab.mine.dao.bean.Col;
import cn.sixlab.mine.dao.service.ApiService;
import cn.sixlab.mine.dao.utils.JsonParam;
import cn.sixlab.mine.dao.utils.JsonResult;
import cn.sixlab.mine.dao.utils.JsonUtil;
import cn.sixlab.mine.dao.utils.OpType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/json")
public class ApiJsonController {

    @Autowired
    private ApiService service;

    @RequestMapping("/api")
    public JsonResult api(@RequestBody JsonParam param) {
        switch (param.getOpType()) {
            case CHECK:
                return check(param);
            case DROP:
                return drop(param);
            case ADD:
                return add(param);
            case DEL:
                return del(param);
            case UPDATE:
                return update(param);
            case QUERY:
                return query(param);
            default:
                return JsonResult.error("操作类型不支持：" + param.getOpType());
        }
    }

    @RequestMapping("/drop")
    public JsonResult drop(@RequestBody JsonParam param) {
        service.dropTable(param.getTableName());
        return JsonResult.success();
    }

    /**
     * map 中
     * List<Col> cols = (List<Col>) data.get("cols");
     *
     * @param param
     * @return
     */
    @RequestMapping("/check")
    public JsonResult check(@RequestBody JsonParam param) {
        if (service.checkTable(param.getTableName())) {
            return JsonResult.success();
        } else {
            service.createTable(param.getTableName(), param.getData());
            return JsonResult.success("创建表成功");
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();

        List<Col> cols = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Col col = new Col();
            col.setName("name_" + i);
            col.setVal("varchar(10)");

            cols.add(col);
        }
        for (int i = 0; i < 2; i++) {
            Col col = new Col();
            col.setName("age_" + i);
            col.setVal("int(11)");

            cols.add(col);
        }
        map.put("cols", cols);

        JsonParam param = new JsonParam();
        param.setData(map);
        param.setTableName("my_test");
        param.setOpType(OpType.CHECK);

        System.out.println(JsonUtil.toJsonFormat(param));
    }

    /**
     * map 中
     * List<Col> cols = (List<Col>) data.get("cols");
     *
     * @param param
     * @return
     */
    @RequestMapping("/add")
    public JsonResult add(@RequestBody JsonParam param) {
        service.add(param.getTableName(), param.getData());
        return JsonResult.success();
    }

    /**
     * map 中
     * Integer id = MapUtils.getInteger(data, "id");
     *
     * @param param
     * @return
     */
    @RequestMapping("/del")
    public JsonResult del(@RequestBody JsonParam param) {
        Map result = service.del(param.getTableName(), param.getData());

        Map data = new HashMap();
        data.put("result", result);
        return JsonResult.success(data);
    }

    /**
     * map 中
     * Integer id = MapUtils.getInteger(data, "id");
     * List<Col> cols = (List<Col>) data.get("cols");
     *
     * @param param
     * @return
     */
    @RequestMapping("/update")
    public JsonResult update(@RequestBody JsonParam param) {
        Map result = service.update(param.getTableName(), param.getData());

        Map data = new HashMap();
        data.put("result", result);
        return JsonResult.success(data);
    }

    /**
     * map 中
     * List<String> cols = (List<String>) data.get("cols");
     * ist<String> cnds = (List<String>) data.get("cnds");
     * String orders = MapUtils.getString(data, "orders");
     *
     * @param param
     * @return
     */
    @RequestMapping("/query")
    public JsonResult query(@RequestBody JsonParam param) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", service.query(param.getTableName(), param.getData()));
        return JsonResult.success(data);
    }

    // @RequestMapping("/query/{table}")
    // public JsonResult query(@RequestBody JsonParam param) {
    //     log.info("query");
    //
    //     this.query(param.getTableName(), 1, 10);
    // }
    //
    //
    // @RequestMapping("/query/{table}/{pageNo}")
    // public JsonResult query(@RequestBody JsonParam param) {
    //     log.info("query no");
    //     this.query(param.getTableName(), pageNo, 10);
    // }
    //
    //
    // @RequestMapping("/query/{table}/{pageNo}/{pageSize}")
    // public JsonResult query(@PathVariable("table") String table, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
    //     log.info("query no size");
    // }

}
