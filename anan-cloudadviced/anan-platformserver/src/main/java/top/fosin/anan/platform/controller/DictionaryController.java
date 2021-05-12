package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryUpdateDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.service.inter.DictionaryService;
import top.fosin.anan.platformapi.entity.AnanDictionaryEntity;

/**
 * Description 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.DICTIONARY)
@Api(value = UrlPrefixConstant.DICTIONARY, tags = "通用字典管理(增删改查)")
public class DictionaryController implements ISimpleController<AnanDictionaryEntity, Long, AnanDictionaryCreateDto, AnanDictionaryRetrieveDto, AnanDictionaryUpdateDto> {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }
}
