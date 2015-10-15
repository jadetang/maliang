import com.typesafe.config.ConfigFactory;
import github.jadetang.maliang.MEngine;
import github.jadetang.maliang.conf.MConfig;
import github.jadetang.maliang.resource.Resources;

import java.io.File;
import java.io.IOException;

/**
 * @Author Tang Sicheng
 */
public class Main {


    private static MConfig mConfig = new MConfig(ConfigFactory.load());

    private static String sql = "CREATE TABLE user (\n" +
            "  age INT,\n" +
            "  NAME VARCHAR(32),\n" +
            "  sex VARCHAR\n" +
            ");" +
            "create table salary(" +
            "name varchar(32)," +
            "pay int)";

    public static void main(String[] args) throws IOException {

        File customConfig = Resources.getResourceAsFile(Main.class.getClassLoader(),"SimpleExample/custom.conf");
        MEngine engine = new MEngine(customConfig);
        engine.generateCode(sql);

       /* Path simpleExample = Paths.get("example/src/main/resources/SimpleExample");
        Path outPut = Paths.get("example/target/output");

        JavaObjectBuilder javaObjectBuilder = new JavaObjectBuilder(mConfig);
        Tuple<JavaClass,Table> t = javaObjectBuilder.parser(sql);
        VelocityContext c = ContextBuilder.build(t._1(),t._2(),mConfig);

        //  System.out.println(m.getBasePackage());
        SourceCodeGenerator generator = new SourceCodeGenerator(simpleExample.toAbsolutePath().toString(), outPut.toAbsolutePath().toString(), mConfig);
        generator.init();
        generator.generate(c);*/
    }


}
