package $${basePackage}.service.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName $${TableName}
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/2/10
 * @Version V1.0
 **/
 
@Transactional
public interface BaseServ<O> {
	public boolean doAdd(O o);
	public boolean doDel(O o);
	public boolean doUpd(O o);
	public List<O> doSel(O o);
}
