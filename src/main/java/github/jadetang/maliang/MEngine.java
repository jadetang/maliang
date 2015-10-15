package github.jadetang.maliang;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import github.jadetang.maliang.bean.JavaDataBaseRelation;
import github.jadetang.maliang.builder.ContextBuilder;
import github.jadetang.maliang.builder.JavaDataBaseRelationBuilder;
import github.jadetang.maliang.builder.SourceCodeGenerator;
import github.jadetang.maliang.conf.MConfig;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @Author Tang Sicheng
 */
public class MEngine {

    private MConfig mConfig;
    private JavaDataBaseRelationBuilder javaObjectBuilder;
    private SourceCodeGenerator generator;

    /**
     * Construct an instance with default configuration
     */
    public MEngine() {
        this(ConfigFactory.empty());
    }

    /**
     * Construct an instance with custom properties
     * @param properties custom properties
     */
    public MEngine(Properties properties) {
        this(ConfigFactory.parseProperties(properties));
    }

    /**
     * Construct an instance with custom properties written in file
     * @param file
     */
    public MEngine(File file){
        this(ConfigFactory.parseFile(file));
    }


    public MEngine(Config config) {
        Config defaultConfig = ConfigFactory.load();
        mConfig = new MConfig(config.withFallback(defaultConfig));
        javaObjectBuilder = new JavaDataBaseRelationBuilder(mConfig);
        generator = new SourceCodeGenerator(mConfig);
    }


    public void generateCode(String createStatement) throws IOException {
        List<JavaDataBaseRelation> relations = javaObjectBuilder.parserMulit(createStatement);
        generator.init();
        for (JavaDataBaseRelation relation : relations) {
            VelocityContext context = ContextBuilder.build(relation, mConfig);
            generator.generate(context);
        }
    }


}
