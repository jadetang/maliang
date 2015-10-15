package github.jadetang.maliang.conf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MConfigTest {


    private MConfig mConfig;

    @Before
    public void setUp(){
        Config c = ConfigFactory.load();
        Assert.assertNotNull(c);
        mConfig = new MConfig(c);
    }



    @Test
    public void testType(){
        Assert.assertEquals("java.lang.String",mConfig.getJavaType("varchar"));
    }
}