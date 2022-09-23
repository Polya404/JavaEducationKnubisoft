package knubisoft.base.validation.dao;

import knubisoft.base.validation.ValidationTasks;
import knubisoft.base.validation.ValidationTasksImpl;

import java.util.Optional;

public class UserAddressDetailsDaoImpl implements UserAddressDetailsDao {
    private final ValidationTasks instance = new ValidationTasksImpl();

    public Optional<ValidationTasks.UserAddressDetails> getById(Long id) {
        return Optional.ofNullable(instance.buildUserAddressDetails());
    }
}
