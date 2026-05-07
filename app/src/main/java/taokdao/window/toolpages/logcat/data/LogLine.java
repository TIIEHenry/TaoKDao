package taokdao.window.toolpages.logcat.data;

import android.util.Log;

import taokdao.window.toolpages.logcat.reader.ScrubberUtils;

public class LogLine {
    public static boolean isScrubberEnabled = false;
    private int logLevel = -1;
    private String tag = "";
    private String logOutput = "";
    private int processId = -1;
    private String timestamp = "";
    private boolean expanded = false;
    private boolean highlighted = false;

    private static char convertLogLevelToChar(int logLevel) {

        switch (logLevel) {
            case Log.DEBUG:
                return 'D';
            case Log.ERROR:
                return 'E';
            case Log.INFO:
                return 'I';
            case Log.VERBOSE:
                return 'V';
            case Log.WARN:
                return 'W';
//            case LogLineAdapterUtil.LOG_WTF:
//                return 'F';
        }
        return ' ';
    }

    public String getOriginalLine() {

        if (logLevel == -1) { // starter line like "begin of log etc. etc."
            return logOutput;
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (timestamp != null) {
            stringBuilder.append(timestamp).append(' ');
        }

        stringBuilder.append(convertLogLevelToChar(logLevel))
                .append('/')
                .append(tag)
                .append('(')
                .append(processId)
                .append("): ")
                .append(logOutput);

        return stringBuilder.toString();
    }

    public String getLogLevelText() {
        return Character.toString(convertLogLevelToChar(logLevel));
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLogOutput() {
        return logOutput;
    }

    public void setLogOutput(String logOutput) {
        if (isScrubberEnabled) {
            this.logOutput = ScrubberUtils.scrubLine(logOutput);
        } else {
            this.logOutput = logOutput;
        }
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

}
