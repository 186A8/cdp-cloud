package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanParameterEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface ParameterRepository extends IJpaRepository<AnanParameterEntity, Long> {
    AnanParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<AnanParameterEntity> findByStatusNot(Integer status);

    @Query(value = "select * from anan_parameter where " +
            "((((type = 1 and scope in (select id from anan_organization where code like ?2))" +
            " or (type = 2 and scope in (select id from anan_user where organiz_id in (select id from anan_organization where code like ?2)))" +
            "or type = 3 or scope is null or scope = '') and ?3 = 1) or (?3 = 2)) and (name like ?1 or value like " +
            "?1 or description" +
            " like ?1 " +
            "or " +
            "default_value like ?1)", nativeQuery = true)
    Page<AnanParameterEntity> findPage(String search, String code, Integer type, Pageable pageable);
}
