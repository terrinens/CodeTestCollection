package Tools.Report;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MDTag {
    public MDTag(String startHeader, String endHeader) {
        this.startHeader = startHeader;
        this.endHeader = endHeader;
        startHaderList = new ArrayList<>(List.of(startHeader, ""));
        endHaderList = new ArrayList<>(List.of(endHeader));
    }

    public MDTag(String startHeader, String endHeader, String content) {
        this.startHeader = startHeader;
        this.endHeader = endHeader;
        this.content = content;
        this.startHaderList = new ArrayList<>(List.of(startHeader, content));
        this.endHaderList = new ArrayList<>(List.of(endHeader));
    }

    private final String startHeader;
    private final String endHeader;
    private String content = "";

    private final List<String> startHaderList;
    private final List<String> endHaderList;

    public void appendTag(MDTag newMdTag) {
        this.startHaderList.addAll(List.of(newMdTag.startHeader, newMdTag.content));
        this.endHaderList.add(newMdTag.endHeader);
    }

    public List<String> getResult() {
        Collections.reverse(endHaderList);
        return Stream.of(startHaderList, endHaderList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    public String getStartHeader() {
        return startHeader;
    }

    public String getEndHeader() {
        return endHeader;
    }

    public String getContent() {
        return content;
    }
}
