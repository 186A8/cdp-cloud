package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.mvc.service.ISimpleService;

import java.util.List;

/**
 * 系统机构授权表(cdp_sys_organization_auth)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysOrganizationAuthService extends ISimpleService<CdpSysOrganizationAuthEntity, Long> {
    List<CdpSysOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<CdpSysOrganizationAuthEntity> findAllByOrganizId(Long organizId);
}