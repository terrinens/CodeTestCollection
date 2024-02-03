package Tools;

import java.io.IOException;
import java.nio.file.Path;

public class Checks {
    /** 파일이 존재하는지 확인합니다. 파일에 대상은 폴더도 포함 되어있습니다. */
    public static void existsCheck(Path path) {
        ifException(path, !path.toFile().exists(), "해당 위치를 찾지 못했습니다.");
    }

    /** 폴더 존재 여부를 확인하고, 폴더인지 확인하는 메서드입니다. */
    public static void folderCheck(Path path) {
        existsCheck(path);
        ifException(path, !path.toFile().isDirectory(), "해당 위치는 폴더가 아닙니다.");
    }

    /**
     * 오류 생성기입니다. true일 경우에 오류를 반환합니다.
     *
     * @param path      메세지에 사용될 경로값입니다.
     * @param condition 조건 입니다.
     * @param massage   오류에 포함될 메세지 입니다.
     */
    private static void ifException(Path path, boolean condition, String massage) {
        if (condition) {
            String log = String.format("\n{%s} %s", path, massage);
            throw new RuntimeException(log, new IOException(path.toString()));
        }
    }
}
