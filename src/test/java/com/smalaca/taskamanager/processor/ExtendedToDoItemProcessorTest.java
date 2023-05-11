package com.smalaca.taskamanager.processor;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.smalaca.taskamanager.model.entities.Project;
import com.smalaca.taskamanager.model.entities.Story;
import com.smalaca.taskamanager.model.entities.Task;
import com.smalaca.taskamanager.registry.EventsRegistry;
import com.smalaca.taskamanager.service.CommunicationService;
import com.smalaca.taskamanager.service.ProjectBacklogService;
import com.smalaca.taskamanager.service.SprintBacklogService;
import com.smalaca.taskamanager.service.StoryService;
import java.util.List;
import org.junit.jupiter.api.Test;

class ExtendedToDoItemProcessorTest {
    private final StoryService storyService = mock(StoryService.class);
    private final EventsRegistry eventsRegistry = mock(EventsRegistry.class);
    private final ProjectBacklogService projectBacklogService = mock(ProjectBacklogService.class);
    private final CommunicationService communicationService = mock(CommunicationService.class);
    private final SprintBacklogService sprintBacklogService = mock(SprintBacklogService.class);

    private final ToDoItemProcessor processor = new ToDoItemProcessorExtended(
            storyService, eventsRegistry, projectBacklogService, communicationService, sprintBacklogService);

    @Test
    void shouldProcessDefinedNotAssignedStoryWithTasks() {
        //given
        Project project = mock(Project.class);
        Story story = mock(Story.class);
        List<Task> tasks = asList(mock(Task.class), mock(Task.class));
        given(story.getTasks()).willReturn(tasks);
        given(story.isAssigned()).willReturn(false);
        given(story.getProject()).willReturn(project);

        //when
        processor.processDefined(story);

        //then
        then(story).should().getTasks();
        then(story).should().isAssigned();
        then(story).should().getProject();
        then(communicationService).should().notifyTeamsAbout(story, project);
        verifyNoMoreInteractions(story, storyService, eventsRegistry, projectBacklogService, communicationService, sprintBacklogService);
    }
    
}