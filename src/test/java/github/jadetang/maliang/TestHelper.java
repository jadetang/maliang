package github.jadetang.maliang;

import com.google.common.io.CharStreams;
import com.typesafe.config.ConfigFactory;
import github.jadetang.maliang.bean.JavaClass;
import github.jadetang.maliang.builder.JavaDataBaseRelationBuilder;
import github.jadetang.maliang.conf.MConfig;
import github.jadetang.maliang.resource.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author Tang Sicheng
 */
public class TestHelper {


    public static JavaClass buildJavaClass(String sqlFile) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(sqlFile);
        String sql = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
        JavaDataBaseRelationBuilder objectBuilder = new JavaDataBaseRelationBuilder(new MConfig(ConfigFactory.load()));
        JavaClass javaClass = objectBuilder.parser(sql).getBean();
        return javaClass;
    }

}
