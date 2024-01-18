package ru.ilynoiz.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ilynoiz.tacocloud.tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
