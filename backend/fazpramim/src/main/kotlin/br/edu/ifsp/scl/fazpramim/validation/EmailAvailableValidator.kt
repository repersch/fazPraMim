package br.edu.ifsp.scl.fazpramim.validation

import br.edu.ifsp.scl.fazpramim.service.PersonService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(var personService: PersonService): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) {
            return false
        }
        return personService.emailAvailable(value)
    }

}
