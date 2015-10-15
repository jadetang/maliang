#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ${bean.name}{

    public ${bean.name}(){}

#foreach ( $field in $bean.getFields() )
    private $field.type ${StringUtil.lowerFirstLetter(${field.name})};

#end
#foreach ( $field in $bean.getFields() )
    public void set${field.name}($field.type ${StringUtil.lowerFirstLetter(${field.name})}){
        this.${StringUtil.lowerFirstLetter(${field.name})} = ${StringUtil.lowerFirstLetter(${field.name})};
    }

    public $field.type get${field.name}(){
        return ${StringUtil.lowerFirstLetter(${field.name})};
    }
#end

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
