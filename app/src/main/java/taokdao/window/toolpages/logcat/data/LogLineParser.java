package taokdao.window.toolpages.logcat.data;


import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLineParser {

    private static final int TIMESTAMP_LENGTH = 19;

    private static final Pattern logPattern = Pattern.compile(
            // log level
            "(\\w)" +
                    "/" +
                    // tag
                    "([^(].+)" +
                    "\\(\\s*" +
                    // pid
                    "(\\d+)" +
                    // optional weird number that only occurs on ZTE blade
                    "(?:\\*\\s*\\d+)?" +
                    "\\): ");


    public static LogLine parseLogLine(String originalLine) {
        LogLine logLine = new LogLine();

        int startIdx = 0;

        // if the first char is a digit, then this starts out with a timestamp
        // otherwise, it's a legacy log or the beginning of the log output or something
        if (!TextUtils.isEmpty(originalLine)
                && Character.isDigit(originalLine.charAt(0))
                && originalLine.length() >= TIMESTAMP_LENGTH) {
            String timestamp = originalLine.substring(0, TIMESTAMP_LENGTH - 1);
            logLine.setTimestamp(timestamp);
            startIdx = TIMESTAMP_LENGTH; // cut off timestamp
        }

        Matcher matcher = logPattern.matcher(originalLine);

        if (matcher.find(startIdx)) {
            char logLevelChar = matcher.group(1).charAt(0);

            String logText = originalLine.substring(matcher.end());
            if (logText.matches("^maxLineHeight.*|Failed to read.*")) {
//                logLine.setLogLevel("V");
                logLine.setLogLevel(convertCharToLogLevel('V'));
            } else {
//                logLine.setLogLevel(String.valueOf(logLevelChar));
                logLine.setLogLevel(convertCharToLogLevel(logLevelChar));
            }

            String tagText = matcher.group(2);
//            if (tagText.matches(filterPattern)) {
//                logLine.setLogLevel(convertCharToLogLevel('V'));
//            }

            logLine.setTag(tagText);
            logLine.setProcessId(Integer.parseInt(matcher.group(3)));

            logLine.setLogOutput(logText);

        } else {
//            log.d("Line doesn't match pattern: %s", originalLine);
            logLine.setLogOutput(originalLine);
            logLine.setLogLevel(-1);
        }

        return logLine;

    }

    private static int convertCharToLogLevel(char logLevelChar) {
        switch (logLevelChar) {
            case 'D':
                return Log.DEBUG;
            case 'E':
                return Log.ERROR;
            case 'I':
                return Log.INFO;
            case 'V':
                return Log.VERBOSE;
            case 'W':
                return Log.WARN;
//            case 'F':
//                return LogLineAdapterUtil.LOG_WTF; // 'F' actually stands for 'WTF', which is a real Android log level in 2.2
        }
        return -1;
    }

}
