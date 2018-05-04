package data_entry.json;

public class Expression {
    private String property;
    private String value;
    private String operator;
    private String matchType;
    private String expressionType = "";

    public Expression(String property, String value, String operator) {
        this.property = property;
        this.value = value;
        this.operator = operator;
    }

    public Expression(String value) {
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }

    public String getMatchType() {
        return matchType;
    }

    public String getExpressionType() {
        return expressionType;
    }
}
