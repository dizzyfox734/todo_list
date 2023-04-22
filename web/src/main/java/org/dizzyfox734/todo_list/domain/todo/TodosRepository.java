package org.dizzyfox734.todo_list.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodosRepository extends JpaRepository<Todos, Long> {

    @Query("SELECT t FROM Todos t ORDER BY t.id DESC")
    List<Todos> findAllDesc();
}
