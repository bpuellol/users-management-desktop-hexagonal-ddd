package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.service.CreateUserService;
import com.jcaa.usersmanagement.application.service.DeleteUserService;
import com.jcaa.usersmanagement.application.service.EmailNotificationService;
import com.jcaa.usersmanagement.application.service.GetAllUsersService;
import com.jcaa.usersmanagement.application.service.GetUserByIdService;
import com.jcaa.usersmanagement.application.service.LoginService;
import com.jcaa.usersmanagement.application.service.UpdateUserService;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.application.port.in.child.CreateChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.UpdateChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.DeleteChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetChildByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenWithStatusUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetInactiveChildrenUseCase;
import com.jcaa.usersmanagement.application.service.child.CreateChildService;
import com.jcaa.usersmanagement.application.service.child.UpdateChildService;
import com.jcaa.usersmanagement.application.service.child.DeleteChildService;
import com.jcaa.usersmanagement.application.service.child.GetChildByIdService;
import com.jcaa.usersmanagement.application.service.child.GetAllChildrenService;
import com.jcaa.usersmanagement.application.service.child.GetAllChildrenWithStatusService;
import com.jcaa.usersmanagement.application.service.child.GetInactiveChildrenService;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.repository.ChildRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.application.port.in.allergy.CreateAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.UpdateAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.DeleteAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetAllergyByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetAllAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetChildrenWithAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetForbiddenDishesByChildUseCase;
import com.jcaa.usersmanagement.application.service.allergy.CreateAllergyService;
import com.jcaa.usersmanagement.application.service.allergy.UpdateAllergyService;
import com.jcaa.usersmanagement.application.service.allergy.DeleteAllergyService;
import com.jcaa.usersmanagement.application.service.allergy.GetAllergyByIdService;
import com.jcaa.usersmanagement.application.service.allergy.GetAllAllergiesService;
import com.jcaa.usersmanagement.application.service.allergy.GetChildrenWithAllergiesService;
import com.jcaa.usersmanagement.application.service.allergy.GetForbiddenDishesByChildService;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.repository.AllergyRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.application.port.in.dish.CreateDishUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.UpdateDishUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.DeleteDishUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.GetDishByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.GetAllDishesUseCase;
import com.jcaa.usersmanagement.application.service.dish.CreateDishService;
import com.jcaa.usersmanagement.application.service.dish.UpdateDishService;
import com.jcaa.usersmanagement.application.service.dish.DeleteDishService;
import com.jcaa.usersmanagement.application.service.dish.GetDishByIdService;
import com.jcaa.usersmanagement.application.service.dish.GetAllDishesService;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.repository.DishRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller.DishController;
import java.sql.Connection;
import jakarta.validation.Validator;

public final class DependencyContainer {

    private static final String DB_HOST     = "db.host";
    private static final String DB_PORT     = "db.port";
    private static final String DB_NAME     = "db.name";
    private static final String DB_USER     = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static final String SMTP_HOST      = "smtp.host";
    private static final String SMTP_PORT      = "smtp.port";
    private static final String SMTP_USER      = "smtp.username";
    private static final String SMTP_PASSWORD  = "smtp.password";
    private static final String SMTP_FROM      = "smtp.from.address";
    private static final String SMTP_FROM_NAME = "smtp.from.name";

    private final UserController    userController;
    private final ChildController   childController;
    private final AllergyController allergyController;
    private final DishController    dishController;

    public DependencyContainer() {
        final AppProperties properties = new AppProperties();
        final Connection    connection = buildDatabaseConnection(properties);
        final Validator     validator  = ValidatorProvider.buildValidator();

        // ── Users ─────────────────────────────────────────────────────────────────
        final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);
        final JavaMailEmailSenderAdapter emailSender =
                new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
        final EmailNotificationService emailNotification =
                new EmailNotificationService(emailSender);

        final CreateUserUseCase  createUserUseCase  = new CreateUserService(userRepository, userRepository, emailNotification, validator);
        final UpdateUserUseCase  updateUserUseCase  = new UpdateUserService(userRepository, userRepository, userRepository, emailNotification, validator);
        final DeleteUserUseCase  deleteUserUseCase  = new DeleteUserService(userRepository, userRepository, validator);
        final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdService(userRepository, validator);
        final GetAllUsersUseCase getAllUsersUseCase  = new GetAllUsersService(userRepository);
        final LoginUseCase       loginUseCase       = new LoginService(userRepository, validator);

        this.userController = new UserController(
                createUserUseCase, updateUserUseCase, deleteUserUseCase,
                getUserByIdUseCase, getAllUsersUseCase, loginUseCase);

        // ── Children ──────────────────────────────────────────────────────────────
        final ChildRepositoryMySQL childRepository = new ChildRepositoryMySQL(connection);

        final CreateChildUseCase              createChildUseCase             = new CreateChildService(childRepository, childRepository, validator);
        final UpdateChildUseCase              updateChildUseCase             = new UpdateChildService(childRepository, childRepository, validator);
        final DeleteChildUseCase              deleteChildUseCase             = new DeleteChildService(childRepository, childRepository, validator);
        final GetChildByIdUseCase             getChildByIdUseCase            = new GetChildByIdService(childRepository, validator);
        final GetAllChildrenUseCase           getAllChildrenUseCase           = new GetAllChildrenService(childRepository);
        final GetAllChildrenWithStatusUseCase getAllChildrenWithStatusUseCase = new GetAllChildrenWithStatusService(childRepository);
        final GetInactiveChildrenUseCase      getInactiveChildrenUseCase     = new GetInactiveChildrenService(childRepository);

        this.childController = new ChildController(
                createChildUseCase, updateChildUseCase, deleteChildUseCase,
                getChildByIdUseCase, getAllChildrenUseCase,
                getAllChildrenWithStatusUseCase, getInactiveChildrenUseCase);

        // ── Allergies ─────────────────────────────────────────────────────────────
        final AllergyRepositoryMySQL allergyRepository = new AllergyRepositoryMySQL(connection);

        final CreateAllergyUseCase createAllergyUseCase = new CreateAllergyService(allergyRepository, allergyRepository, validator);
        final UpdateAllergyUseCase updateAllergyUseCase = new UpdateAllergyService(allergyRepository, allergyRepository, validator);
        final DeleteAllergyUseCase deleteAllergyUseCase = new DeleteAllergyService(allergyRepository, allergyRepository, validator);
        final GetAllergyByIdUseCase getAllergyByIdUseCase = new GetAllergyByIdService(allergyRepository, validator);
        final GetAllAllergiesUseCase getAllAllergiesUseCase = new GetAllAllergiesService(allergyRepository);
        final GetChildrenWithAllergiesUseCase  getChildrenWithAllergiesUseCase = new GetChildrenWithAllergiesService(allergyRepository);
        final GetForbiddenDishesByChildUseCase getForbiddenDishesByChildUseCase = new GetForbiddenDishesByChildService(allergyRepository);

        this.allergyController = new AllergyController(
                createAllergyUseCase, updateAllergyUseCase, deleteAllergyUseCase,
                getAllergyByIdUseCase, getAllAllergiesUseCase,
                getChildrenWithAllergiesUseCase, getForbiddenDishesByChildUseCase);

        // ── Dishes ────────────────────────────────────────────────────────────────
        final DishRepositoryMySQL dishRepository = new DishRepositoryMySQL(connection);

        final CreateDishUseCase createDishUseCase = new CreateDishService(dishRepository, dishRepository, validator);
        final UpdateDishUseCase updateDishUseCase = new UpdateDishService(dishRepository, dishRepository, validator);
        final DeleteDishUseCase deleteDishUseCase = new DeleteDishService(dishRepository, dishRepository, validator);
        final GetDishByIdUseCase getDishByIdUseCase = new GetDishByIdService(dishRepository, validator);
        final GetAllDishesUseCase getAllDishesUseCase = new GetAllDishesService(dishRepository);

        this.dishController = new DishController(
                createDishUseCase, updateDishUseCase, deleteDishUseCase,
                getDishByIdUseCase, getAllDishesUseCase);
    }

    public UserController    userController()    { return userController; }
    public ChildController   childController()   { return childController; }
    public AllergyController allergyController() { return allergyController; }
    public DishController    dishController()    { return dishController; }

    private static Connection buildDatabaseConnection(final AppProperties properties) {
        final DatabaseConfig config = new DatabaseConfig(
                properties.get(DB_HOST),
                properties.getInt(DB_PORT),
                properties.get(DB_NAME),
                properties.get(DB_USER),
                properties.get(DB_PASSWORD));
        return DatabaseConnectionFactory.createConnection(config);
    }

    private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
        return new SmtpConfig(
                properties.get(SMTP_HOST),
                properties.getInt(SMTP_PORT),
                properties.get(SMTP_USER),
                properties.get(SMTP_PASSWORD),
                properties.get(SMTP_FROM),
                properties.get(SMTP_FROM_NAME));
    }
}
