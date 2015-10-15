package github.jadetang.maliang.builder;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import github.jadetang.maliang.bean.*;
import github.jadetang.maliang.bean.JavaClass.JavaClassBuilder;
import github.jadetang.maliang.bean.JavaField.JavaFieldBuilder;
import github.jadetang.maliang.conf.MConfig;
import github.jadetang.maliang.exception.MaliangException;
import github.jadetang.maliang.util.ContextUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * using jsqlparser to parser a create table
 * then convert the result to {@link github.jadetang.maliang.bean.JavaDataBaseRelation}
 * @author TANG SICHENG
 */
public class JavaDataBaseRelationBuilder {

    private MConfig mConfig;

    public JavaDataBaseRelationBuilder(MConfig mConfig) {
        this.mConfig = mConfig;
        this.tablePrefix = mConfig.getTablePrefix();
        this.columnPrefix = mConfig.getColumnPrefix();
        this.separator = mConfig.getSeparator();
    }

    private String tablePrefix;

    private String separator;

    private String columnPrefix;


    private Tuple<JavaClass, Table> parseToTuple(String createSQL) {
        try {
            Statement statement = CCJSqlParserUtil.parse(createSQL);
            if (statement instanceof CreateTable) {
                JavaClass javaClass=  buildJavaClass((CreateTable) statement);
                Table table = buildTable((CreateTable) statement);
                return new Tuple<>(javaClass,table);
            } else {
                throw new MaliangException("only accept create statement");
            }
        } catch (JSQLParserException e) {
            throw new MaliangException(e);

        }
    }


    public JavaDataBaseRelation parser(String createSQL){
        Tuple<JavaClass, Table>  tuple = parseToTuple(createSQL);
        return new JavaDataBaseRelation(tuple._1(),tuple._2());
    }




    public List<JavaDataBaseRelation> parserMulit(String createSQLs){
        List<String> sqls = Lists.newLinkedList(Splitter.on(";").omitEmptyStrings().trimResults().split(createSQLs));
        return Lists.transform(sqls, new Function<String, JavaDataBaseRelation>() {
            public JavaDataBaseRelation apply(String input) {
                return parser(input);
            }
        });
    }



    private Table buildTable(CreateTable createStatement) {
        Table.TableBuilder tableBuilder = new Table.TableBuilder();
        tableBuilder.withName(createStatement.getTable().getName().toUpperCase());
        tableBuilder.withColumns(extractColumns(createStatement));
        return tableBuilder.build();
    }

    private LinkedList<Column> extractColumns(CreateTable createStatement) {
        LinkedList<Column> columns = Lists.newLinkedList();
        List<ColumnDefinition> columnDefinitions = createStatement.getColumnDefinitions();
        for (ColumnDefinition c:columnDefinitions){
            Column.ColumnBuilder cBuilder = new Column.ColumnBuilder();
            cBuilder.withName(c.getColumnName().toUpperCase());
            cBuilder.withType(c.getColDataType().getDataType());
            columns.add(cBuilder.build());
        }
        return columns;
    }

    private JavaClass buildJavaClass(CreateTable createStatement) {
        JavaClassBuilder jBuilder  = new JavaClassBuilder();
        String tableName = createStatement.getTable().getName();
        String javaClassName = transformName(tableName,tablePrefix,separator);
        jBuilder.withName(javaClassName);
        jBuilder.withFields(extractFields(createStatement));
        return jBuilder.build();
    }

    /**
     * transform a database name to a java name,for example, a table in database called
     * T_APP_USER,then with prefix T
     * @param originName database object name
     * @param prefix database object name prefix
     * @param separator database object separator
     * @return
     */
    private String transformName(String originName,String prefix,String separator){
        if(StringUtils.isNotBlank(originName)){
            originName = StringUtils.removeStart(originName,prefix);
        }
        if(StringUtils.isNotBlank(separator)){
            List<String> names = Splitter.on(separator).omitEmptyStrings().trimResults().splitToList(originName);
            List<String> javaStyleNames = Lists.transform(names, new Function<String, String>() {
                public String apply(String input) {
                    return ContextUtil.onlyFirstLetterIsUp(input);
                }
            });
            originName = Joiner.on("").join(javaStyleNames);
        }else {
            originName = ContextUtil.onlyFirstLetterIsUp(originName);
        }
        return ContextUtil.upperFirstLetter(originName);
    }

    private LinkedList<JavaField> extractFields(CreateTable createStatement) {
        LinkedList<JavaField> javaFields = Lists.newLinkedList();
        List<ColumnDefinition> columnDefinitions = createStatement.getColumnDefinitions();
        for (ColumnDefinition c:columnDefinitions){
            javaFields.add(convertColumnToField(c));
        }
        return javaFields;
    }

    private JavaField convertColumnToField(ColumnDefinition columnDefinition){
        JavaFieldBuilder jfBuilder = new JavaFieldBuilder();
        String columnName = columnDefinition.getColumnName();
        String javaFieldName = transformName(columnName,columnPrefix,separator);
        jfBuilder.withName(javaFieldName);
        String javaType = mConfig.getJavaType(columnDefinition.getColDataType().getDataType());
        jfBuilder.withType(javaType);
        return jfBuilder.build();
    }


}
