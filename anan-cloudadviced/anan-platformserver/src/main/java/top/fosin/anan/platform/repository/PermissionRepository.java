package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platform.entity.AnanPermissionEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface PermissionRepository extends IJpaRepository<AnanPermissionEntity, Long> {
    //    @Query(value = "select menu.* from anan_menu menu,anan_role_privilege p where menu.id=p.menu_id and p.role_id=?1",nativeQuery = true)
//    List<AnanPermissionEntity> findRoleMenuByRoleId(Integer roleId);
//
//    @Query(value = "select menu.* from anan_menu menu,anan_user_privilege p where menu.id=p.menu_id and p.user_id=?1 and p.role_id=?2 and p.add_mode=?3",nativeQuery = true)
//    List<AnanPermissionEntity> findUserMenuByUserIdAndRoleIdAndAddMode(Integer userId, Integer roleId, Integer addMode);
//
//    @Query(value = "select m.* from anan_menu m,(select p.role_id,p.menu_id,r.add_mode from anan_role_privilege p left join anan_user_privilege r on p.role_id =r.role_id and p.menu_id=r.menu_id  and r.user_id=?1  where p.role_id=?2 and (r.add_mode = 0 or r.add_mode is null)) z where m.id=z.menu_id union select m.* from anan_menu m ,anan_user_privilege r where m.id=r.menu_id and r.role_id=?2 and r.user_id=?1 and r.add_mode=0",nativeQuery = true)
//    List<AnanPermissionEntity> getAllMenuByUserIdAndRoleId(Integer userId, Integer roleId);
    List<AnanPermissionEntity> findByPid(Long pid);

    @Query(value = "select * from anan_permission where p_id = :pid and id in (select permission_id from anan_version_permission where version_id = :versionId) order by sort", nativeQuery = true)
    List<AnanPermissionEntity> findAllByPidAndVersionId(@Param(value = TreeDto.PID_NAME) Long pid, @Param(value = "versionId") Long versionId);

    List<AnanPermissionEntity> findAllByServiceId(Long serviceId);

    List<AnanPermissionEntity> findByServiceIdIn(List<Long> serviceIds);
}
