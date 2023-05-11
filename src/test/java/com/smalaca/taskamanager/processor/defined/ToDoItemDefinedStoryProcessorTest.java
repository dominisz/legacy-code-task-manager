package com.smalaca.taskamanager.processor.defined;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import com.smalaca.taskamanager.model.entities.Epic;
import com.smalaca.taskamanager.model.entities.Project;
import com.smalaca.taskamanager.model.entities.Story;
import com.smalaca.taskamanager.model.entities.Task;
import com.smalaca.taskamanager.service.CommunicationService;
import com.smalaca.taskamanager.service.ProjectBacklogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ToDoItemDefinedStoryProcessorTest {

  @InjectMocks private ToDoItemDefinedStoryProcessor toDoItemDefinedStoryProcessor;
  @Mock private ProjectBacklogService projectBacklogService;
  @Mock private CommunicationService communicationService;

  @Test
  void shouldProcessStoryWithoutTasks() {
    // given
    var project = new Project();
    var story = createStory(project);

    // when
    toDoItemDefinedStoryProcessor.process(story);

    // then
    verify(projectBacklogService).moveToReadyForDevelopment(story, project);
  }

  @Test
  void shouldProcessNotAssignedStoryWithTasks() {
    // given
    var project = new Project();
    var story = createStory(project);
    story.addTask(new Task());

    // when
    toDoItemDefinedStoryProcessor.process(story);

    // then
    verify(communicationService).notifyTeamsAbout(story, project);
  }

  @Test
  void shouldProcessAssignedStoryWithTasks() {}

  private Story createStory(Project project) {
    var epic = new Epic();
    epic.setProject(project);
    var story = new Story();
    story.setEpic(epic);
    return story;
  }
}
