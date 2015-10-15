package github.jadetang.maliang.conf;

import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import java.util.Map;
import java.util.Set;

/**
 * @author TANG SICHENG
 */
public class MConfig {

    private String groupId;

    private String artifactId;

    private String version;

    private String basePackage;

    private Config config;

    private Map<String, String> typeMap;


    public String getJavaType(String sqlType) {
        return typeMap.get(sqlType.toUpperCase());
    }

    public MConfig(Config config) {
        config.checkValid(ConfigFactory.defaultReference(), "maven");
        this.groupId = config.getString("maven.groupId");
        this.artifactId = config.getString("maven.artifactId");
        this.version = config.getString("maven.version");
        this.basePackage = config.getString("maven.package");
        this.typeMap = buildMap(config.getConfig("database.type"));
        this.config = config;
    }

    private Map<String, String> buildMap(Config config) {
        Set<Map.Entry<String, com.typesafe.config.ConfigValue>> entrySet = config.entrySet();
        Map<String, String> m = Maps.newHashMap();
        for (Map.Entry<String, ConfigValue> e : entrySet) {
            m.put(e.getKey(), String.valueOf(e.getValue().unwrapped()));
        }
        return m;
    }

    public String getVersion() {
        return version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getOutputDir() {
        return this.config.getString("dir.output");
    }

    public String getTemplateDir() {
        return this.config.getString("dir.template");
    }

    public String getTablePrefix() {
        return this.config.getString("name.table.prefix");
    }

    public String getSeparator() {
        return this.config.getString("name.separator");
    }

    public String getColumnPrefix() {
        return this.config.getString("name.column.prefix");
    }


}
