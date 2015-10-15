package github.jadetang.maliang.bean;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @author TANG SICHENG
 */
public class JavaDataBaseRelation {

    private JavaClass bean;
    private Table table;
    private LinkedList<Relation> relations;

    public JavaDataBaseRelation(JavaClass bean,Table table){
        this.bean = bean;
        this.table = table;
        this.relations = getRelations(bean,table);
    }

    private LinkedList<Relation> getRelations(JavaClass bean, Table table) {
        LinkedList<Relation> relations = Lists.newLinkedList();
        assert bean.getFields().size() == table.getColumns().size();
        int length = bean.getFields().size();
        for (int i = 0; i < length; i++) {
            relations.add(new Relation(bean.getFields().get(i),table.getColumns().get(i)));
        }
        return relations;
    }

    public JavaClass getBean() {
        return bean;
    }

    public void setBean(JavaClass bean) {
        this.bean = bean;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LinkedList<Relation> getRelations() {
        return relations;
    }

    public void setRelations(LinkedList<Relation> relations) {
        this.relations = relations;
    }
}
