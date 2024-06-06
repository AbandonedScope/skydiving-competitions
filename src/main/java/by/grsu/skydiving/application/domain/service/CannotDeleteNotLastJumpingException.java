package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.BusinessException;

public class CannotDeleteNotLastJumpingException
    extends BusinessException {
    public CannotDeleteNotLastJumpingException() {
        super(
            "Попытка удалить прыжок с парашютом, не являющийся последним для этого спортсмена в данном соревновании.");
    }
}
