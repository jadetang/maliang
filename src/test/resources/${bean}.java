#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )



package ${basePackage}


public class ${StringUtil.upperFirstLetter(${bean.name})}{

    public ${bean.name}(){}


#foreach ( $field in $bean.getFields() )
    private $field.type $field.name;
#end


}
