package top.fosin.anan.platform.service;

import top.fosin.anan.platform.repository.AnanPayRepository;
import top.fosin.anan.platform.service.inter.AnanPayService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanPayServiceImpl implements AnanPayService {
    private final AnanPayRepository ananSysPayRepository;

    public AnanPayServiceImpl(AnanPayRepository ananSysPayRepository) {
        this.ananSysPayRepository = ananSysPayRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanPayRepository getRepository() {
        return ananSysPayRepository;
    }
}
