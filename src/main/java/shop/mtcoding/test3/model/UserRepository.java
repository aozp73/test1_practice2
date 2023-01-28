package shop.mtcoding.test3.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    public int insert(@Param("username") String username, @Param("password") String password,
            @Param("email") String email) throws Exception;

    public List<User> findAll();

    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    public User findById(int id);

    public int updateById(@Param("id") int id, @Param("password") String password, @Param("email") String email);

    public int deleteById(int id);

}
