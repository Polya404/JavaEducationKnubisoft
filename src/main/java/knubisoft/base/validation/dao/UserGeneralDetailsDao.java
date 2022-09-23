package knubisoft.base.validation.dao;

import knubisoft.base.validation.ValidationTasks;

import java.util.Optional;

public interface UserGeneralDetailsDao {
    Optional<ValidationTasks.UserAddressDetails> getById(Long id);
}
