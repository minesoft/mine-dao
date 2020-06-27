package cn.sixlab.mine.dao.controller;

import cn.sixlab.mine.dao.aop.TableName;
import cn.sixlab.mine.dao.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/dao")
public class ApiController {

    @Autowired
    private ApiService service;

    @TableName
    @RequestMapping("/add/{table}/{id}")
    public void add(@PathVariable("table") String table, @RequestBody Map map) {

    }

    @TableName
    @RequestMapping("/modify/{table}/{id}")
    public void modify(@PathVariable("table") String table, @PathVariable("id") Integer id, @RequestBody Map map) {

    }

    @TableName
    @RequestMapping("/delete/{table}/{id}")
    public void delete(@PathVariable("table") String table, @PathVariable("id") Integer id) {

    }

    @TableName
    @RequestMapping("/queryAll/{table}")
    public void queryAll(@PathVariable("table") String table) {

    }

    @TableName
    @RequestMapping("/query/{table}")
    public void query(@PathVariable("table") String table) {
        log.info("query");

        this.query(table, 1, 10);
    }

    @TableName
    @RequestMapping("/query/{table}/{pageNo}")
    public void query(@PathVariable("table") String table, @PathVariable("pageNo") Integer pageNo) {
        log.info("query no");
        this.query(table, pageNo, 10);
    }

    @TableName
    @RequestMapping("/query/{table}/{pageNo}/{pageSize}")
    public void query(@PathVariable("table") String table, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        log.info("query no size");
    }
    
}
