package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CalendarTabs {
    YESTERDAY("YESTERDAY"),
    TODAY("TODAY"),
    TOMORROW("TOMORROW"),
    THIS_WEEK("THIS_WEEK");

    final String filterTabs;
}
