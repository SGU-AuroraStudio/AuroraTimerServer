package aurora.timer.server.dao.idao;

import aurora.timer.server.vo.UserData;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Set;

/**
 * Created by hao on 17-1-16.
 */
public interface IUserDataDAO {
    /**
     * 创建一个UserData，即创建新用户
     * @param vo 传入一个UserData类型的对象作为vo表
     * @return 创建成功返回true，否则返回false
     * @throws Exception
     */
    public boolean doCreate(UserData vo) throws Exception;

    /**
     * 更新一个UserData，传入一个UserData类型的对象，根据其ID进行更新
     * @param vo 传入UserData对象
     * @return 更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean doUpdate(UserData vo) throws Exception;

    /**
     * 根据ID删除UserData
     * @param ids 传入ID的Set集合
     * @return 成功则返回true，有一个或一个以上删除失败则返回false
     * @throws Exception
     */
    public boolean doRemoveBatch(Set<String> ids) throws Exception;

    /**
     * 根据ID查找用户
     * @param id 传入用户的ID
     * @return 查找成功返回UserData，否则返回NULL
     * @throws Exception
     */
    public UserData findById(String id) throws Exception;

    /**
     * 根据id更新背景图片
     * @param id 传入用户的ID
     * @param bg 传入背景图片 InputStream
     * @return 成功则返回true，有一个或一个以上删除失败则返回false
     * @throws Exception
     */
    public boolean updateBgById(String id, InputStream bg) throws Exception;

    /**
     * 根据id查询背景图片
     * @param id 传入用户的ID
     * @return 背景图片的InputStream
     * @throws Exception
     */
    public InputStream findBgById(String id) throws Exception;
}
