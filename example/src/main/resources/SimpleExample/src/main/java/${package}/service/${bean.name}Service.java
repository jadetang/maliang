package ${package}.service;

import ${package}.dao.${bean.name}Dao;
import ${package}.model.${bean.name};
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;

public interface  ${bean.name}Service{

    Integer insert(${bean.name} entity);

    Integer update(${bean.name} entity);

    List select(${bean.name} entity,PageBounds pageBounds);

    List<${bean.name}> selectAll();

    Integer delete(${bean.name} entity);
}
