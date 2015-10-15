package github.jadetang.maliang.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author Tang Sicheng
 */
public class Column {

    private String name;

    private String type;

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Column(){}

    private Column(ColumnBuilder builder){
        this.name = builder.name;
        this.type = builder.type;
    }

    public static class ColumnBuilder{
        private String name;
        private String type;
        public ColumnBuilder(){}

        public ColumnBuilder withName(String name){
            this.name = name;
            return this;
        }

        public  ColumnBuilder withType(String type){
            this.type = type;
            return this;
        }

        public Column build(){
            return new Column(this);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
