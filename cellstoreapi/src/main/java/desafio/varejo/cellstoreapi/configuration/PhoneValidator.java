package desafio.varejo.cellstoreapi.configuration;

import desafio.varejo.cellstoreapi.model.Phone;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PhoneValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Phone.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Phone phone = (Phone) target;

        if (verifyDates(phone.getStartDate(), phone.getEndDate()))
            errors.rejectValue("startDate",
                    "phone.startDate.greaterThanOrEqualToEndDate",
                    "Data de início da venda não pode ser posterior ou igual que a data de término"
            );

        if (verifyUrls(phone.getPhoto()))
            errors.rejectValue("photo",
                    "phone.photo.invalidFormat",
                    "Formato de URL da foto inválido"
            );

        if (verifyCode(phone.getCode()))
            errors.rejectValue("photo",
                    "phone.code.sizeNot8",
                    "Tamanho do código diferente de 8"
            );
    }

    private boolean verifyCode(@NotNull String code) {
        return code.length() != 8;
    }

    private boolean verifyDates(@NotNull LocalDate start, @NotNull LocalDate end) {
        return start.compareTo(end) >= 0;
    }

    private boolean verifyUrls(@NotNull String url) {
        return new URLValidator().isValid(url, null);
    }
}
