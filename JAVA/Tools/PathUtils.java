package Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathUtils {
    private static final BiFunction<Stream<Path>, List<Predicate<Path>>, Path[]> filterStream =
            (stream, predicates) -> predicates.stream()
                    .reduce(stream, Stream::filter, (s1, s2) -> s2)
                    .toArray(Path[]::new);

    /**
     * 해당 메서드를 사용할시, 실제로는 참고 메서드에 ignores가 null로 전달됩니다. <br>
     * {@link PathUtils#getSubFiles(Path, Path...)}
     */
    public static Path[] getSubFiles(Path path) {

        return getSubFiles(path, (Path) null);
    }

    /**
     * 하위 폴더에서 폴더를 제외한 파일만 가져오기위한 메서드 입니다. <br>
     * 매개 변수 'ignores'에 특정 디렉토리나 파일을 지정시 해당되는 객체를 제외한 나머지의 파일을 가져옵니다.
     *
     * @param path    부모 디렉토리의 위치입니다.
     * @param ignores 특정 위치를 무시하고 가져오기 위한 매개변수입니다. null이 허용됩니다.
     */
    public static Path[] getSubFiles(Path path, Path... ignores) {
        Checks.folderCheck(path);

        try (Stream<Path> pathStream = Files.list(path)) {
            Predicate<Path> notIgnore = (targetPath) -> notIgnorePattern(path, ignores);
            return (ignores == null)
                    ? filterStream.apply(pathStream, List.of(Files::isRegularFile))
                    : filterStream.apply(pathStream, List.of(Files::isRegularFile, notIgnore));

        } catch (IOException e) {
            String exceptionMassage = String.format("%s " +
                    "경로에서 작업중 오류가 발생 했습니다. " +
                    "순회중 파일이 변경 되었거나 삭제 되었습니다. " +
                    "%s", path, e
            );
            throw new RuntimeException(exceptionMassage);
        }
    }

    /**
     * 해당 메서드를 사용할시, 실제로는 참고 메서드에 ignores가 null로 전달됩니다. <br>
     * {@link PathUtils#getSubFiles(Path, Path...)}
     */
    public static Path[] getSubFolders(Path path) {
        return getSubFolders(path, (Path) null);
    }

    public static Path[] getSubFolders(Path path, Path... ignores) {
        Checks.folderCheck(path);
        Predicate<Path> notIgnore = (targetPath) -> notIgnorePattern(path, ignores);
        try (Stream<Path> pathStream = Files.list(path)) {
            return ignores == null
                    ? filterStream.apply(pathStream, List.of(Files::isDirectory))
                    : filterStream.apply(pathStream, List.of(Files::isDirectory, notIgnore));

        } catch (IOException e) {
            String exceptionMassage = String.format("%s " +
                    "경로에서 작업중 오류가 발생 했습니다. " +
                    "순회중 파일이 변경 되었거나 삭제 되었습니다. " +
                    "%s", path, e
            );
            throw new RuntimeException(exceptionMassage);
        }
    }


    /**
     * 해당 메서드는 현재 폴더가 무시 대상인지 아닌지 확인하기 위한 메서드입니다.
     * dirPath로 넘겨 받은 디렉토리가 ignores에 해당되는 폴더일 경우에 'false'를 반환합니다.
     * 모든 무시 대상에 해당되지 않을 경우에 'true'를 반환합니다.
     *
     * @param dirPath 검사 할 디렉토리의 위치입니다.
     * @param ignores 검사시 사용될 디렉토리의 위치들입니다. </br>
     *                해당 위치가 폴더일 경우에 하위 폴더가 전부 무시 처리 됩니다. </br>
     *                하지만 텍스트 형식 혹은 gitignore 형태를 가지고 있다면 glob 형식으로 변환되어 검사를 실행합니다.
     */
    public static boolean notIgnorePattern(Path dirPath, Path... ignores) {
        boolean result = true;
        for (Path ignorePath : ignores) {
            if (Files.isDirectory(ignorePath) && ignorePath.equals(dirPath)) {
                result = false;
                break;
            } else if (Files.isRegularFile(ignorePath)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(ignorePath.toFile()))) {
                    result = reader.lines()
                            .filter(l -> !l.startsWith("#") && !l.startsWith("!") && !l.isBlank())
                            .map(line -> FileSystems.getDefault().getPathMatcher("glob:" + line))
                            .noneMatch(line -> line.matches(dirPath));
                } catch (IOException e) {
                    throw new RuntimeException(String.format("%s 폴더를 찾지 못했습니다.", ignorePath));
                }
            }
        }

        return result;
    }


    /**
     * 해당 메서드는 주어진 매개 변수 'path'의 하위 디렉토리들을 순회하고, 오직 파일인 객체만 가져옵니다. <br>
     * 깊이 수준은 1으로 하위 폴더의 하위 폴더까지는 포함되지 않습니다.
     *
     * @param path 작업을 진행할 디렉토리 경로값
     * @return 'path'의 하위 폴더의 이름 key | 하의 폴더의 파일 리스트 value
     */
    public static Map<Path, List<Path>> mapOfSubDirInFiles(Path path, String specificDir, String specificExtend) {
        /*하던중*/
        Map<Path, List<Path>> map = new HashMap<>();
        try (Stream<Path> fileList = Files.list(path)) {
            Path[] subDirs = fileList.filter(Files::isDirectory)
                    .toArray(Path[]::new);

            for (Path subDir : subDirs) {
                Path specDir = Optional.of(specificDir)
                        .map(subDir::resolve)
                        .orElse(subDir);

                Stream<Path> subFileList = Files.list(specDir);

                List<Path> javaFiles = null;

                Stream<Path> test = subFileList.filter(Files::isRegularFile)
                        .map(Path::getFileName);


                //map.put(clearDir, javaFiles);

                subFileList.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
