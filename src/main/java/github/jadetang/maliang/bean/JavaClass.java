package github.jadetang.maliang.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedList;

/**
 * @author TANG SICHENG
 */
public class JavaClass {

    private String name;

    private LinkedList<JavaField> fields;

    private JavaClass() {
    }

    private JavaClass(JavaClassBuilder builder) {
        this.name = builder.name;
        this.fields = builder.javaFields;
    }

    public String getName() {
        return name;
    }

    public LinkedList<JavaField> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static class JavaClassBuilder {

        private String name;
        private LinkedList<JavaField> javaFields;

        public JavaClassBuilder() {
        }

        public JavaClassBuilder withName(String name) {
            JavaClassBuilder.this.name = name;
            return this;
        }

        public JavaClassBuilder withFields(LinkedList<JavaField> fields) {
            JavaClassBuilder.this.javaFields = new LinkedList<JavaField>(fields);
            return this;
        }

        public JavaClass build() {
            return new JavaClass(this);
        }
    }

}
