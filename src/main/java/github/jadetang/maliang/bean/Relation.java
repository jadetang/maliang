package github.jadetang.maliang.bean;

/**
 * @author TANG SICHENG
 */
public class Relation extends Tuple<JavaField,Column> {

    private JavaField field;

    private Column column;

    public Relation(JavaField first, Column second) {
        super(first, second);
        this.field = first;
        this.column = second;
    }

    public JavaField bean(){
        return super._1();
    }

    public Column table(){
        return super._2();
    }

    public JavaField getField() {
        return field;
    }

    public void setField(JavaField field) {
        this.field = field;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }
}
