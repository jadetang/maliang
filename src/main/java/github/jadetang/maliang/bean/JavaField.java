package github.jadetang.maliang.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * @author TANG SICHENG
 */
public class JavaField {

    private String name;

    /**
     * represent a field type like Integer,Double,etc
     */
    private String type;

    private JavaField(){}

    private JavaField(JavaFieldBuilder javaFieldBuilder){
        this.name = javaFieldBuilder.name;
        this.type = javaFieldBuilder.type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static class JavaFieldBuilder{
        private String name;
        private String type;

        public JavaFieldBuilder(){}

        public JavaFieldBuilder withName(String name){
            JavaFieldBuilder.this.name = name;
            return this;
        }

        public JavaFieldBuilder withType(String type){
            JavaFieldBuilder.this.type = type;
            return this;
        }

        public JavaField build(){
            return new JavaField(this);
        }

    }

}
