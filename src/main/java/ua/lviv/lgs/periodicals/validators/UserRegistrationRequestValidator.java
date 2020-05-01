package ua.lviv.lgs.periodicals.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.lviv.lgs.periodicals.dtos.UserRegistrationRequest;

@Component
public class UserRegistrationRequestValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return UserRegistrationRequest.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserRegistrationRequest request = (UserRegistrationRequest) target;
    if(request.getFirstName().isEmpty()) {
      errors.rejectValue("firstName", "empty");
    }
  }
}
