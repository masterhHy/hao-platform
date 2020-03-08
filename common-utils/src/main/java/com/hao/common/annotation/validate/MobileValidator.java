package com.hao.common.annotation.validate;

import com.hao.common.annotation.IsMobile;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验是否为合法的手机号码
 *
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = "[1]\\d{10}";
                Pattern pattern = Pattern.compile(regex);
                return pattern.matcher(s).matches();
            }
        } catch (Exception e) {
            return false;
        }
    }
}
