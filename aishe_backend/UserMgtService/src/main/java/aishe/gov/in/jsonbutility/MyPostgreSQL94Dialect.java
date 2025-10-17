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
       
        this.registerColumnType(Types.JAVA_OBJECT, "json");
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb_array_elements");
        this.registerFunction("jsonb_text",  new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("jsonb_array_elements",  new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("json_array_elements",  new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("json_array_elements_text",  new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("json_array_element_text",  new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("json_extract_path_text",new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
        this.registerFunction("jsonb_extract_path_text",new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
      //  this.registerFunction("jsonb_extract_path",new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
       // this.registerFunction("::jsonb?",new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));
      //  this.registerFunction("->",  new SQLFunctionTemplate(StringType.INSTANCE, "?1 ->> ?2"));    
    }
}
