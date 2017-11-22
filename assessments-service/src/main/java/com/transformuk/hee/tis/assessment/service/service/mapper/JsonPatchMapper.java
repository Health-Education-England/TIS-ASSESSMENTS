package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.JsonPatchDTO;
import com.transformuk.hee.tis.assessment.service.model.JsonPatch;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity JsonPatch and its DTO JsonPatchDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JsonPatchMapper {

  JsonPatchDTO jsonPatchToJsonPatchDTO(JsonPatch jsonPatch);

  List<JsonPatchDTO> jsonPatchesToJsonPatchDTOs(List<JsonPatch> jsonPatches);

  JsonPatch jsonPatchDTOToJsonPatch(JsonPatchDTO jsonPatchDTO);

  List<JsonPatch> jsonPatchDTOsToJsonPatches(List<JsonPatchDTO> jsonPatchDTOs);
}
