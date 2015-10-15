package github.jadetang.maliang.builder;

import com.google.common.io.CharStreams;
import com.typesafe.config.ConfigFactory;
import github.jadetang.maliang.bean.JavaClass;
import github.jadetang.maliang.bean.Table;
import github.jadetang.maliang.conf.MConfig;
import github.jadetang.maliang.resource.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JavaObjectBuilderTest {


    @Test
    public void testParse() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("./user.sql");
        String sql = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
        System.out.println(sql);
        JavaDataBaseRelationBuilder objectBuilder = new JavaDataBaseRelationBuilder(new MConfig(ConfigFactory.load()));
        JavaClass javaClass = objectBuilder.parser(sql).getBean();
        Table table = objectBuilder.parser(sql).getTable();
        System.out.println(javaClass);
        Assert.assertEquals("User", javaClass.getName());
        System.out.println(javaClass.getFields());

        System.out.println(table);

    }
}
