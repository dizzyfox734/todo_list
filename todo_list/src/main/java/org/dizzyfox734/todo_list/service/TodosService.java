package org.dizzyfox734.todo_list.service;

import org.dizzyfox734.todo_list.domain.todo.Todos;
import org.dizzyfox734.todo_list.domain.todo.TodosRepository;
import org.dizzyfox734.todo_list.web.dto.TodosListResponseDto;
import org.dizzyfox734.todo_list.web.dto.TodosResponseDto;
import org.dizzyfox734.todo_list.web.dto.TodosSaveRequestDto;
import org.dizzyfox734.todo_list.web.dto.TodosUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodosService {
    private TodosRepository todosRepository;

    @Autowired
    public TodosService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    @Transactional
    public Long save(TodosSaveRequestDto requestDto) {
        return todosRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, TodosUpdateRequestDto requestDto) {
        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 todo는 존재하지 않습니다. id=" + id));
        todos.update(requestDto.getContent(), requestDto.getCompleted_fl());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 todo는 존재하지 않습니다. id=" + id));

        todosRepository.delete(todos);
    }

    public TodosResponseDto findById(Long id) {
        Todos entity = todosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 todo는 존재하지 않습니다. id=" + id));

        return new TodosResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<TodosListResponseDto> findAllDesc() {
        return todosRepository.findAllDesc().stream()
                .map(TodosListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long complete(Long id) {
        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 todo는 존재하지 않습니다. id=" + id));
        todos.complete();

        return id;
    }
}
