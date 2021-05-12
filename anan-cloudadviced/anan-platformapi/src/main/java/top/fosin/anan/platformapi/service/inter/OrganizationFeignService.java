package top.fosin.anan.platformapi.service.inter;


import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import top.fosin.anan.platformapi.service.OrganizationFeignFallbackServiceImpl;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.ORGANIZATION, fallback = OrganizationFeignFallbackServiceImpl.class)
public interface OrganizationFeignService {
    @PostMapping({"/{id}"})
    @ApiOperation("根据主键ID查询一条数据")
    ResponseEntity<AnanOrganizationEntity> findOne(@PathVariable(TreeDto.ID_NAME) Long id);

    @PostMapping("/listChild/{pid}")
    ResponseEntity<List<AnanOrganizationEntity>> listChild(@PathVariable(TreeDto.PID_NAME) Long pid);

    @PostMapping("/listAllChild/{pid}")
    ResponseEntity<List<AnanOrganizationEntity>> listAllChild(@PathVariable(TreeDto.PID_NAME) Long pid);

    @PostMapping("/tree/{topId}")
    ResponseEntity<List<AnanOrganizationEntity>> tree(@PathVariable("topId") Long topId);

}

