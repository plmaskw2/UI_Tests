package framework.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Forum {
    private String topicSubject;
    private String category;
    private String question;
}
