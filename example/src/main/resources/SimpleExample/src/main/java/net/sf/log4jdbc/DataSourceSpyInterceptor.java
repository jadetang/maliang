package net.sf.log4jdbc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.sql.Connection;

/**
 * @author badqiu
 */
public class DataSourceSpyInterceptor implements MethodInterceptor {

    private RdbmsSpecifics rdbmsSpecifics = null;

    private RdbmsSpecifics getRdbmsSpecifics(Connection conn) {
        if(rdbmsSpecifics == null) {
            rdbmsSpecifics = DriverSpy.getRdbmsSpecifics(conn);
        }
        return rdbmsSpecifics;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if(SpyLogFactory.getSpyLogDelegator().isJdbcLoggingEnabled()) {
            if(result instanceof Connection) {
                Connection conn = (Connection)result;
                return new ConnectionSpy(conn,DriverSpy.getRdbmsSpecifics(conn));
            }
        }
        return result;
    }
}
