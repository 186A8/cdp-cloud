package top.fosin.anan.platform.repository;

import top.fosin.anan.platform.entity.AnanVersionEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
/**
 * 系统版本表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanVersionRepository extends IJpaRepository<AnanVersionEntity, Long> {
}
