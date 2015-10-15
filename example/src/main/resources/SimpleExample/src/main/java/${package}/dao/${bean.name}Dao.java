package ${config.basePackage}.dao;

import java.util.List;
import ${config.basePackage}.model.${bean.name};

import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Repository("${StringUtil.lowerFirstLetter(${bean.name})}Dao")
public class ${bean.name}Dao extends BaseDao{

    public List select(${bean.name} entity,PageBounds pageBounds) {
        return this.getSqlMap().selectList(this.getClass().getName()+".select", entity, pageBounds);
    }

    public List<${bean.name}> selectAll(){
        return this.getSqlMap().selectList(this.getClass().getName()+".selectAll");
    }

    public Integer delete(${bean.name} entity){
        return this.getSqlMap().delete(this.getClass().getName()+".delete",entity);
    }

    public Integer update(${bean.name} entity){
        return this.getSqlMap().update(this.getClass().getName()+".update",entity);
    }

    public Integer insert(${bean.name} entity){
        return this.getSqlMap().insert(this.getClass().getName()+".insert",entity);
    }
}
