package com.transformuk.hee.tis.assessment.service.api.validators;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
public class AsessmentValidator {

  private static final String DTO_NAME = "AssessmentDTO";

  public void validate(AssessmentDTO assessmentDTO) throws MethodArgumentNotValidException {

    List<FieldError> fieldErrors = new ArrayList<>();
    fieldErrors.addAll(checkAssessment(assessmentDTO));

    if (!fieldErrors.isEmpty()) {
      BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(assessmentDTO, DTO_NAME);
      fieldErrors.forEach(bindingResult::addError);
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
  }

  private List<FieldError> checkAssessment(AssessmentDTO assessmentDTO) {
    List<FieldError> fieldErrors = new ArrayList<>();

    return fieldErrors;
  }

}
