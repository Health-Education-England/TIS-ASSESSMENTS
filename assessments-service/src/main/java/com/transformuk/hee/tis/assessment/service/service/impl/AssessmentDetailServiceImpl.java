package com.transformuk.hee.tis.assessment.service.service.impl;

import com.google.common.base.Preconditions;
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
   * @param traineeId the id of the trainee linked to the assessment
   * @param assessmentId the id of the assessment entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<AssessmentDetailDTO> findAssessmentDetailBy(Long traineeId, Long assessmentId) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(assessmentId);

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
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(assessmentDetailDTO);
    Preconditions.checkState(assessment.getId().equals(assessmentDetailDTO.getId()));

    AssessmentDetail assessmentDetail = assessmentDetailMapper.toEntity(assessmentDetailDTO);
    assessmentDetail.setIntrepidId(assessment.getIntrepidId());
    assessmentDetail = assessmentDetailRepository.save(assessmentDetail);
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
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(assessmentDetailDTO);
    Preconditions.checkState(assessmentDetailDTO.getId() == null);

    assessmentDetailDTO.setId(assessment.getId());
    return save(assessment, assessmentDetailDTO);
  }

  /**
   * Find an assessment detail by id
   * @param assessmentDetailId the id of the entity
   * @return the detail matching the ID
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<AssessmentDetailDTO> findOne(Long assessmentDetailId) {
    Preconditions.checkNotNull(assessmentDetailId);

    log.debug("Request to get Assessment Detail : {}", assessmentDetailId);
    AssessmentDetail assessmentDetail = assessmentDetailRepository.findOne(assessmentDetailId);
    AssessmentDetailDTO assessmentDetailDTO = assessmentDetailMapper.toDto(assessmentDetail);
    return Optional.ofNullable(assessmentDetailDTO);
  }
}
