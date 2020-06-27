package cn.sixlab.mine.dao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "mine.dao")
public class Params {
    private Map<String, String> alias;
}
