package ${package}.controller;


import ${package}.util.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseController{

    protected void responseAsJson(HttpServletResponse response,Object object) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.toJson(object));
    }
}
