package ru.ilynoiz.tacocloud.repositories;

import org.springframework.stereotype.Repository;
import ru.ilynoiz.tacocloud.tacos.TacoOrder;

@Repository
public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
