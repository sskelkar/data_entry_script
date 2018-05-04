package data_entry.json;

import java.util.Arrays;
import java.util.List;

public class Constraint {
    private String name;
    private List<Expression> ifExpressions;
    private List<Expression> thenExpressions;

    private List<String> categories = Arrays.asList("BU_Preference");
    private String target;
    private String externalCode;

    public Constraint(String name, List<Expression> ifExpressions, List<Expression> thenExpressions, String target, String externalCode) {
        this.name = name;
        this.ifExpressions = ifExpressions;
        this.thenExpressions = thenExpressions;
        this.target = target;
        this.externalCode = externalCode;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getIfExpressions() {
        return ifExpressions;
    }

    public List<Expression> getThenExpressions() {
        return thenExpressions;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getTarget() {
        return target;
    }

    public String getExternalCode() {
        return externalCode;
    }
}
