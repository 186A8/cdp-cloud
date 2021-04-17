package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.cloudresource.constant.SystemConstant;
import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.cloudresource.dto.RegisterDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanOrganizationCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanOrganizationRetrieveDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanOrganizationUpdateDto;
import com.github.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import com.github.fosin.anan.model.controller.AbstractBaseController;
import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationPermissionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import com.github.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import com.github.fosin.anan.platform.service.inter.AnanOrganizationAuthService;
import com.github.fosin.anan.platform.service.inter.AnanOrganizationPermissionService;
import com.github.fosin.anan.platform.service.inter.OrganizationService;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@RestController
@Slf4j
@RequestMapping(UrlPrefixConstant.ORGANIZATION)
@Api(value = UrlPrefixConstant.ORGANIZATION, tags = "机构管理相关操作(增删改查)")
public class AnanOrganizationController extends AbstractBaseController
        implements ISimpleController<AnanOrganizationEntity, Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto> {
    private final OrganizationService organizationService;
    private final AnanOrganizationAuthService organizationAuthService;
    private final AnanOrganizationPermissionService organizationPermissionService;

    public AnanOrganizationController(OrganizationService organizationService, AnanOrganizationAuthService organizationAuthService, AnanOrganizationPermissionService organizationPermissionService) {
        this.organizationService = organizationService;
        this.organizationAuthService = organizationAuthService;
        this.organizationPermissionService = organizationPermissionService;
    }

    @ApiOperation("根据机构ID获取机构权限")
    @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/permissions/{organizId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanOrganizationPermissionEntity>> permissions(@PathVariable Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.findByOrganizId(organizId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<AnanOrganizationPermissionEntity>)"),
            @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")

    })
    @PutMapping(value = "/permissions/{organizId}")
    public ResponseEntity<Collection<AnanOrganizationPermissionEntity>> permissions(@RequestBody List<AnanOrganizationPermissionUpdateDto> entities,
                                                                                    @PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.updateInBatch(organizId, entities));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @PostMapping("/listChild/{pid}")
    @ApiImplicitParam(name = SystemConstant.PID_NAME, required = true, dataTypeClass = Long.class, value = "父节点ID,AnanOrganizationEntity.id", paramType = "path")
    public ResponseEntity<List<AnanOrganizationEntity>> findChildByPid(@PathVariable(SystemConstant.PID_NAME) Long pid) {
        return ResponseEntity.ok(organizationService.findChildByPid(pid));
    }

    @ApiOperation("根据父机构ID获取其所有后代节点数据")
    @ApiImplicitParam(name = SystemConstant.PID_NAME, required = true, dataTypeClass = Long.class, value = "父节点ID,AnanOrganizationEntity.id", paramType = "path")
    @PostMapping("/listAllChild/{pid}")
    public ResponseEntity<List<AnanOrganizationEntity>> findAllChildByPid(@PathVariable(SystemConstant.PID_NAME) Long pid) {
        return ResponseEntity.ok(organizationService.findAllChildByPid(pid));
    }

    @ApiOperation("根据机构ID获取其以及后代节点数据，树形结构")
    @ApiImplicitParam(name = SystemConstant.ID_NAME, required = true, dataTypeClass = Long.class, value = "父节点ID,AnanOrganizationEntity.id", paramType = "path")
    @PostMapping("/treeAllChild/{id}")
    public ResponseEntity<AnanOrganizationTreeDto> treeAllChildByid(@PathVariable(SystemConstant.ID_NAME) Long id) {
        return ResponseEntity.ok(organizationService.treeAllChildByid(id));
    }

    @ApiOperation(value = "机构注册", notes = "用户自助注册机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registerDto", required = true, dataTypeClass = RegisterDto.class, value = "注册新机构、新用户", paramType = "body")
    })
    @PutMapping(value = "/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(organizationAuthService.register(registerDto));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "organizId", required = true, dataTypeClass = Long.class, value = "机构ID,取值于AnanOrganizationEntity.id", paramType = "path")
    @PostMapping("/auth/{organizId}")
    public ResponseEntity<AnanOrganizationAuthEntity> getOrganizAuth(@PathVariable("organizId") Long organizId) {
        List<AnanOrganizationAuthEntity> organizationAuthEntities = organizationAuthService.findAllByOrganizId(organizId);
        Assert.isTrue(organizationAuthEntities.size() > 0, "该机构还未购买服务器!");
        return ResponseEntity.ok(organizationAuthEntities.get(0));
    }

    @Override
    public ISimpleService<AnanOrganizationEntity, Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto> getService() {
        return organizationService;
    }
}
