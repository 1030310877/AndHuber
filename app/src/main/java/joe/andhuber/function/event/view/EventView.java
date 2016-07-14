package joe.andhuber.function.event.view;

import java.util.List;

import joe.githubapi.model.event.EventInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/11.
 */
public interface EventView {
    void showEvents(List<EventInfo> data);

    void clearEvents();
}
