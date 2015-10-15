#define( $dao ) ${StringUtil.lowerFirstLetter(${bean.name})}Dao#end
package ${package}.service.impl;

import ${package}.dao.${bean.name}Dao;
import ${package}.model.${bean.name};
import ${package}.service.${bean.name}Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("${beanRef}ServiceImpl")
public class ${bean.name}ServiceImpl implements ${bean.name}Service{

    @Autowired
    private ${bean.name}Dao ${dao};

    public Integer insert(${bean.name} entity){
         return ${dao}.insert(entity);
    }

    public Integer update(${bean.name} entity){
        return ${dao}.update(entity);
    }

    public List select(${bean.name} entity,PageBounds pageBounds){
        return ${dao}.select(entity,pageBounds);
    }

    public List<${bean.name}> selectAll(){
        return ${dao}.selectAll();
    }

    public Integer delete(${bean.name} entity){
        return ${dao}.delete(entity);
    }

}
