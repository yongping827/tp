package ay2021s1_cs2103_w16_3.finesse.storage;

import static ay2021s1_cs2103_w16_3.finesse.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Title;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class JsonAdaptedExpenseTest {
    private static final String INVALID_TITLE = "R@chel";
    private static final String INVALID_AMOUNT = "+651234";
    private static final String INVALID_DATE = "example.com";
    private static final String INVALID_CATEGORY = "#friend";

    private static final String VALID_TITLE = BENSON.getTitle().toString();
    private static final String VALID_AMOUNT = BENSON.getAmount().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = BENSON.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        Expense expenseBenson = new TransactionBuilder(BENSON).buildExpense();
        JsonAdaptedExpense expense = new JsonAdaptedExpense(expenseBenson);
        assertEquals(expenseBenson, expense.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(INVALID_TITLE, VALID_AMOUNT, VALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(null, VALID_AMOUNT, VALID_DATE,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_TITLE, INVALID_AMOUNT, VALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_TITLE, null, VALID_DATE,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_TITLE, VALID_AMOUNT, INVALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_TITLE, VALID_AMOUNT, null,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORY));
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_TITLE, VALID_AMOUNT, VALID_DATE, invalidCategories);
        assertThrows(IllegalValueException.class, expense::toModelType);
    }

}
