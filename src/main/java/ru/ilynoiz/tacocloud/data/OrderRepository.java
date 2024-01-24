package ru.ilynoiz.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.ilynoiz.tacocloud.security.User;
import ru.ilynoiz.tacocloud.tacos.TacoOrder;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
