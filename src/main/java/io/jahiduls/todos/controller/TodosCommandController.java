package io.jahiduls.todos.controller;

import io.jahiduls.todos.commands.CommandFactory;
import io.jahiduls.todos.processors.CommandBus;
import io.jahiduls.todos.resources.TodoResource;
import io.jahiduls.todos.validators.TodoValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class TodosCommandController {

    private final CommandBus commandBus;
    private final CommandFactory factory;
    private final TodoValidator validator;

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody final TodoResource resource) {

        log.info("[{}] - add request received.", resource.getId());

        validator.validate(resource);
        commandBus.emit(factory.addCommand(resource));

        log.info("[{}] - command emitted to add a todo", resource.getId());
    }

    @PutMapping("/todo/update")
    public void update(@RequestBody final TodoResource resource) {

        log.info("[{}] - edit request received", resource.getId());

        validator.validate(resource);
        commandBus.emit(factory.updateCommand(resource));

        log.info("[{}] - command emitted to edit the todo.", resource.getId());
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable final String id) {

        log.info("[{}] - delete request received", id);

        commandBus.emit(factory.deleteCommand(id));

        log.info("[{}] - command emitted to delete the todo.", id);
    }

    @PutMapping("todo/toggle/{id}")
    public void toggle(@PathVariable final String id) {

        log.info("[{}] - toggle request received.", id);

        commandBus.emit(factory.toggleCommand(id));

        log.info("[{}] - command emitted to toggle the todo.", id);

    }

}
