package br.edu.ifsp.scl.fazpramim.validation

import br.edu.ifsp.scl.fazpramim.service.UserService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(val userService: UserService): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) {
            return false
        }
        return userService.emailAvailable(value)
    }

}
