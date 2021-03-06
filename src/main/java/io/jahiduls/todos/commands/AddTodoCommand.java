package io.jahiduls.todos.commands;

import io.jahiduls.todos.resources.TodoResource;
import io.jahiduls.todos.services.TodoService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public class AddTodoCommand implements Command {

    private final TodoResource resource;

    @Override
    public void execute(final TodoService service) {

        service.saveTodo(resource.toTodo());

        log.info("{} executed successfully", this.getClass().getSimpleName());

    }

}
