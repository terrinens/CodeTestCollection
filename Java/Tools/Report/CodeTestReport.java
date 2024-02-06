package Tools.Report;

import Tools.PathUtils;

import java.nio.file.Path;

public class CodeTestReport {
    public static void main(String[] args) {
        Path codeTestDir = Path.of("D:\\All Conding Space\\Study\\Java\\CodeTest");
        createReport(codeTestDir, null);
    }

    public static void createReport(Path codeDir, Path[] ignores) {
        Path[] subDirs = (ignores == null)
                ? PathUtils.getSubFiles(codeDir)
                : PathUtils.getSubFiles(codeDir, ignores);
    }
}

