package vn.ha.tower_defense.exceptions;

import java.util.ArrayList;
import java.util.List;

public class EnrichableException extends RuntimeException {
    protected List<InfoItem> infoItems = new ArrayList<>();

    protected class InfoItem {
        public String errorContext;
        public String errorCode;
        public String errorMessage;

        public InfoItem(String errorContext, String errorCode, String errorMessage) {
            this.errorContext = errorContext;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }

    public EnrichableException(String errorContext, String errorCode, String errorMessage) {
        this.addInfo(errorContext, errorCode, errorMessage);
    }

    public EnrichableException(String errorContext, String errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.addInfo(errorContext, errorCode, errorMessage);
    }

    public EnrichableException addInfo(String errorContext, String errorCode, String errorMessage) {
        this.infoItems.add(new InfoItem(errorContext, errorCode, errorMessage));

        return this;
    }

    public String getCode() {
        StringBuilder stringBuilder = new StringBuilder();

        infoItems.forEach(infoItem -> {
            stringBuilder.append(getCodeOfInfoItem(infoItem));
        });

        return stringBuilder.toString();
    }

    private String getCodeOfInfoItem(InfoItem infoItem) {
        return "[" + infoItem.errorContext + ":" + infoItem.errorCode + "]";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getCode());
        stringBuilder.append("\n");

        for (int i = infoItems.size() - 1; i >= 0; i--) {
            stringBuilder.append(getCodeOfInfoItem(infoItems.get(i)));
            stringBuilder.append(infoItems.get(i).errorMessage);
            stringBuilder.append("\n");
        }

        if (getMessage() != null) {
            if (getCause() == null) {
                stringBuilder.append(getMessage());
            } else if (!(getMessage().equals(getCause().toString()))) {
                stringBuilder.append(getMessage());
            }
        }

        appendException(stringBuilder, getCause());
        return stringBuilder.toString();
    }

    private void appendException(StringBuilder builder, Throwable throwable) {
        if (throwable == null)
            return;
        appendException(builder, throwable.getCause());
        builder.append(throwable.toString());
        builder.append('\n');
    }
}
