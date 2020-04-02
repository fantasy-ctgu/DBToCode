package $${basePackage}.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName $${TableName}
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/2/10
 * @Version V1.0
 **/
 
public interface CommonMapper<O> extends BaseMapper<O> {
    public List<O> doSelectBySome(O o);
}
