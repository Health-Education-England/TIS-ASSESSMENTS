package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentDetailRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentDetailService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public AssessmentDetailDTO findOne(Long id) {
    log.debug("Request to get Assessment : {}", id);
    Assessment assessment = assessmentRepository.findOne(id);
    return assessmentDetailMapper.toDto(assessment.getDetail());
  }

  /**
   * Save an assessment detail.
   *
   * @param assessmentId the id of the assessment to link the details to
   * @param assessmentDetailDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public AssessmentDetailDTO save(Long assessmentId, AssessmentDetailDTO assessmentDetailDTO) {

    Assessment assessment = assessmentRepository.findOne(assessmentId);

    AssessmentDetail assessmentDetail = assessmentDetailMapper.toEntity(assessmentDetailDTO);
    assessmentDetail = assessmentDetailRepository.save(assessmentDetail);
    assessment.setDetail(assessmentDetail);
    assessmentRepository.save(assessment);
    return assessmentDetailMapper.toDto(assessmentDetail);
  }
}
