package aishe.gov.in.jsonbutility;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StringType;

public class MyPostgreSQL94Dialect extends PostgreSQL94Dialect {
	 
    public MyPostgreSQL94Dialect() {

        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
        this.registerFunction("json_text",
                new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("json_extract_path_text",new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
             
    }
}
