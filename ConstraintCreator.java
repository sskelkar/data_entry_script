package data_entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_entry.json.Constraint;
import data_entry.json.Expression;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

public class ConstraintCreator {
    public static void main(String[]a) {
        System.out.println("CONSTRAINT CREATION - START");
        Map<String, String> panelsMap = CSVReader.readPanelProhibitions();
        List<String> excelRows = ExcelReader.readExcel();
        List<String> mmsCodes = MMSCodeReader.readMMSCodes();

        List<Constraint> constraints = new LinkedList<>();

        for (String row : excelRows) {
            String[]tokens = row.split(",");

            String name = tokens[2];
            String dragonCode = tokens[1].trim();
            if(panelsMap.get(dragonCode) == null) {
                System.out.println("##### Panels not found for dragon code: " + dragonCode);
                continue;
            }

            String brandCategoryValues = getBrandCategoryValuesFor(tokens[6], mmsCodes);
            Expression ifExpressionForBuyerInternal = new Expression("/account/buyer/externalId", tokens[4], "IN");
            Expression ifExpressionForBCInternal = new Expression("/account/brand/category/externalId", brandCategoryValues, "IN");

            Expression ifExpressionForBuyerExternal = new Expression("/buyer/externalId", tokens[4], "IN");
            Expression ifExpressionForBCExternal = new Expression("/brand/category/externalId", brandCategoryValues, "IN");

            Expression thenExpression = new Expression("minimumDistanceToSchool", "", "GREATER_THAN");
            Expression thenExpression2 = new Expression("externalIds.oasis", panelsMap.get(dragonCode), "NOT_IN");
            validatePanelCount(row, Integer.valueOf(tokens[3].trim()), panelsMap.get(dragonCode));

            Constraint internal = new Constraint(name, asList(ifExpressionForBuyerInternal, ifExpressionForBCInternal), asList(thenExpression,thenExpression2), "PROGRAMMATIC", dragonCode);
            Constraint external = new Constraint(name, asList(ifExpressionForBuyerExternal, ifExpressionForBCExternal), asList(thenExpression,thenExpression2), "OASIS", dragonCode);

            constraints.add(internal);
            constraints.add(external);
        }

        System.out.println("CONSTRAINT CREATION - RESULT:");
        try {
            System.out.println(new ObjectMapper().writeValueAsString(constraints));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void validatePanelCount(String currentRow, int expectedCount, String csvPanels) {
        int actualPanelCount = csvPanels.split(",").length;
        if(actualPanelCount != expectedCount) {
            System.out.println("##### Panel count mismatch found for row: " + currentRow);
            System.out.println("Expected count: " + expectedCount + ", actual count: " + actualPanelCount);
        }
    }

    private static String getBrandCategoryValuesFor(String token, List<String> mmsCodes) {
        String[] codes = token.split(" ");
        List<String> resultCodes = new LinkedList<>();
        for(String code : codes) {
            if(code.trim().length() != 0)
                resultCodes.addAll(mmsCodes.stream().filter(mms -> mms.startsWith(code.trim())).collect(Collectors.toList()));
        }
        return String.join(",", resultCodes);
    }
}
