package ru.ilynoiz.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.ilynoiz.tacocloud.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
