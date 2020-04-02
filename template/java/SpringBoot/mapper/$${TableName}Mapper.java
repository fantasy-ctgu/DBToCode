package $${basePackage}.mapper;

import java.util.List;

import $${basePackage}.mapper.base.CommonMapper;
import $${basePackage}.entity.$${TableName};
import org.springframework.stereotype.Component;

/**
 * @ClassName $${TableName}
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/2/10
 * @Version V1.0
 **/

public interface $${TableName}Mapper extends CommonMapper<$${TableName}>{
    public List<$${TableName}> doSelectBySome(@Param("$${tableName}") $${TableName} $${tableName});
}
