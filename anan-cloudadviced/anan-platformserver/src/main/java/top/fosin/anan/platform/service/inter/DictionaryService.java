package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.AnanDictionaryRetrieveDto;
import top.fosin.anan.cloudresource.dto.res.AnanDictionaryRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanDictionaryCreateDto;
import top.fosin.anan.platform.dto.req.AnanDictionaryUpdateDto;
import top.fosin.anan.platform.entity.AnanDictionaryEntity;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface DictionaryService extends ISimpleJpaService<AnanDictionaryEntity, AnanDictionaryRespDto,
        Long, AnanDictionaryCreateDto, AnanDictionaryRetrieveDto, AnanDictionaryUpdateDto> {
}
