package com.smalaca.taskamanager.processor;

import com.smalaca.taskamanager.model.enums.ToDoItemStatus;
import com.smalaca.taskamanager.model.interfaces.ToDoItem;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemProcessorFinder {

  private final Map<ToDoItemStatus, ToDoItemStatusProcessor> toDoItemProcessors;

  public ToDoItemProcessorFinder(ApplicationContext applicationContext) {
    var toDoItemStatusProcessors = applicationContext.getBeansOfType(ToDoItemStatusProcessor.class);
    toDoItemProcessors =
        toDoItemStatusProcessors.values().stream()
            .collect(Collectors.toMap(ToDoItemStatusProcessor::forStatus, Function.identity()));
  }

  public void processFor(ToDoItem toDoItem) {
    toDoItemProcessors.get(toDoItem.getStatus()).process(toDoItem);
  }
}
