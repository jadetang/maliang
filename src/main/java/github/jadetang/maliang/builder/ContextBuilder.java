package github.jadetang.maliang.builder;

import github.jadetang.maliang.bean.JavaDataBaseRelation;
import github.jadetang.maliang.conf.MConfig;
import github.jadetang.maliang.util.ContextUtil;
import org.apache.velocity.VelocityContext;

/**
 * @Author Tang Sicheng
 */
public class ContextBuilder {

    /**
     * populate a {@link org.apache.velocity.VelocityContext}
     * @param relation
     * @return
     */
    public static VelocityContext build(JavaDataBaseRelation relation,MConfig config){
        VelocityContext context = new VelocityContext();
        context.put("bean", relation.getBean());
        context.put("table",relation.getTable());
        context.put("relations",relation);
        context.put("config",config);
        context.put("groupId",config.getGroupId());
        context.put("artifactId",config.getArtifactId());
        context.put("version",config.getVersion());
        context.put("package",config.getBasePackage());
        context.put("StringUtil",  ContextUtil.newWrapper());
        return context;
    }
}
