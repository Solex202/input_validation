package task.brilloconnetz.InputValidation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import task.brilloconnetz.InputValidation.model.BrilloconnetzUser;

public interface UserRepository extends MongoRepository<BrilloconnetzUser, String> {
}
