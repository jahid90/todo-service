package io.jahiduls.todos.processors;

import io.jahiduls.todos.queries.Query;
import io.jahiduls.todos.queries.QueryResult;
import io.jahiduls.todos.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TodoQueryProcessor implements QueryProcessor {

    private final TodoService service;

    @Override
    public QueryResult process(final Query query) {
        return query.handle(service);
    }

}
