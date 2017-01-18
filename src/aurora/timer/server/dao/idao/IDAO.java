package aurora.timer.server.dao.idao;

import java.util.Set;

/**
 * Created by hao on 16-12-1.
 */
public interface IDAO<K,V> {
    public boolean doCreate(V vo) throws Exception;
    public boolean doUpdate(V vo) throws Exception;
    public boolean doRemoveBatch(Set<K> ids) throws Exception;
    public K findById(K id) throws Exception;
}
