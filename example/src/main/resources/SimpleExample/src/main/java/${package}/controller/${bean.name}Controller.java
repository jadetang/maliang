#define( $service ) ${StringUtil.lowerFirstLetter(${bean.name})}Service#end
#set( $beanRef = ${StringUtil.lowerFirstLetter(${bean.name})} )

package ${package}.controller;

import ${package}.service.${bean.name}Service;
import ${package}.model.${bean.name};

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/v1/${beanRef}s")
public class ${bean.name}Controller extends BaseController{


    @Autowired
    private ${bean.name}Service ${service};

    @RequestMapping(value="",method=RequestMethod.POST)
    public void insert${bean.name}(${bean.name} $beanRef,HttpServletResponse response) throws IOException {
        Integer affectRecord = ${service}.insert($beanRef);
        responseAsJson(response,affectRecord);
    }

    @RequestMapping(value="",method=RequestMethod.GET)
    public void selectAll${bean.name}(HttpServletResponse response) throws IOException {
        List<${bean.name}> all${bean.name} = ${service}.selectAll();
        responseAsJson(response,all${bean.name});
    }

    @RequestMapping(value="/$beanRef",method=RequestMethod.GET)
    public void select${bean.name}(${bean.name} $beanRef,@RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "10") int limit,HttpServletResponse response)
        throws IOException  {
        List ${bean.name}s =  ${service}.select($beanRef, new PageBounds(page, limit));
        responseAsJson(response,(PageList)${bean.name}s);
    }

    @RequestMapping(value="/$beanRef",method=RequestMethod.DELETE)
    public void delele${bean.name}(${bean.name} $beanRef,HttpServletResponse response )throws IOException {
        Integer affectRecord  =  ${service}.delete($beanRef);
        responseAsJson(response,affectRecord);
    }

    @RequestMapping(value="/$beanRef",method=RequestMethod.PUT)
    public void update${bean.name}(${bean.name} $beanRef,HttpServletResponse response )throws IOException {
        Integer affectRecord  =  ${service}.update($beanRef);
        responseAsJson(response,affectRecord);
    }
}
