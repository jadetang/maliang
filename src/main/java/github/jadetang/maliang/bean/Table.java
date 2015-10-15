package github.jadetang.maliang.bean;

import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedList;

/**
 * A simple pojo representing a table in database
 * @Author Tang Sicheng
 */
public class Table {

    private String name;

    private LinkedList<Column> columns;

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Column> getColumns() {
        return columns;
    }

    public void setColumns(LinkedList<Column> columns) {
        this.columns = columns;
    }

    private Table(){}

    private Table(TableBuilder builder){
        this.name = builder.name;
        this.columns = Lists.newLinkedList(builder.columns);
    }

    public static class TableBuilder{
        private String name;

        private LinkedList<Column> columns;

        public TableBuilder(){}

        public TableBuilder withName(String name){
            this.name = name;
            return this;
        }

        public TableBuilder withColumns(LinkedList<Column> columns){
            this.columns = Lists.newLinkedList(columns);
            return this;
        }

        public Table build(){
            return new Table(this);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
