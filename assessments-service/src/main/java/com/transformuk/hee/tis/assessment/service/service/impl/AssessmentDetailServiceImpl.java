package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentDetailRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentDetailService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AssessmentDetailServiceImpl implements AssessmentDetailService {

  private final Logger log = LoggerFactory.getLogger(AssessmentServiceImpl.class);
  private final AssessmentRepository assessmentRepository;
  private final AssessmentDetailRepository assessmentDetailRepository;
  private final AssessmentDetailMapper assessmentDetailMapper;

  public AssessmentDetailServiceImpl(AssessmentRepository assessmentRepository, AssessmentDetailRepository assessmentDetailRepository,
                                     AssessmentDetailMapper assessmentDetailMapper) {
    this.assessmentRepository = assessmentRepository;
    this.assessmentDetailRepository = assessmentDetailRepository;
    this.assessmentDetailMapper = assessmentDetailMapper;
  }

  /**
   * Get one assessment detail by assessment id.
   *
   * @param assessmentId the id of the assessment entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<AssessmentDetailDTO> findAssessmentDetailBy(String traineeId, Long assessmentId) {
    Assessment example = new Assessment().traineeId(traineeId).id(assessmentId);
    Assessment foundAssessment = assessmentRepository.findOne(Example.of(example));
    if (foundAssessment != null) {
      AssessmentDetailDTO assessmentDetailDTO = assessmentDetailMapper.toDto(foundAssessment.getDetail());
      return Optional.ofNullable(assessmentDetailDTO);
    }
    return Optional.empty();
  }

  /**
   * Save an assessment detail.
   *
   * @param assessment          the assessment to link the details to
   * @param assessmentDetailDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public AssessmentDetailDTO save(Assessment assessment, AssessmentDetailDTO assessmentDetailDTO) {
    AssessmentDetail assessmentDetail = assessmentDetailMapper.toEntity(assessmentDetailDTO);
    assessmentDetail = assessmentDetailRepository.save(assessmentDetail);
    assessment.setDetail(assessmentDetail);
    assessmentRepository.save(assessment);
    return assessmentDetailMapper.toDto(assessmentDetail);
  }

  /**
   * create an assessment detail.
   *
   * @param assessment          the assessment to link the details to
   * @param assessmentDetailDTO the entity to create
   * @return the persisted entity
   */
  @Override
  public AssessmentDetailDTO create(Assessment assessment, AssessmentDetailDTO assessmentDetailDTO) {
    assessmentDetailDTO.setId(assessment.getId());
    return save(assessment, assessmentDetailDTO);
  }

  @Override
  @Transactional(readOnly = true)
  public AssessmentDetailDTO findOne(Long assessmentDetailId) {
    log.debug("Request to get Assessment : {}", assessmentDetailId);
    AssessmentDetail assessmentDetail = assessmentDetailRepository.findOne(assessmentDetailId);
    return assessmentDetailMapper.toDto(assessmentDetail);
  }
}
