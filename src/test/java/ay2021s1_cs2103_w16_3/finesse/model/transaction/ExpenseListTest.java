package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.ALICE;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.exceptions.TransactionNotFoundException;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class ExpenseListTest {

    private static final Expense ALICE_EXPENSE = new TransactionBuilder(ALICE).buildExpense();
    private static final Expense BOB_EXPENSE = new TransactionBuilder(BOB).buildExpense();

    private final ExpenseList expenseList = new ExpenseList();

    @Test
    public void add_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.add(null));
    }

    @Test
    public void setExpense_nullTargetExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpense(null, ALICE_EXPENSE));
    }

    @Test
    public void setExpense_nullEditedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpense(ALICE_EXPENSE, null));
    }

    @Test
    public void setExpense_targetExpenseNotInList_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> expenseList.setExpense(ALICE_EXPENSE, ALICE_EXPENSE));
    }

    @Test
    public void setExpense_editedExpenseIsSameExpense_success() {
        expenseList.add(ALICE_EXPENSE);
        expenseList.setExpense(ALICE_EXPENSE, ALICE_EXPENSE);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(ALICE_EXPENSE);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpense_editedExpenseHasSameIdentity_success() {
        expenseList.add(ALICE_EXPENSE);
        Expense editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).buildExpense();
        expenseList.setExpense(ALICE_EXPENSE, editedAlice);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(editedAlice);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpense_editedExpenseHasDifferentIdentity_success() {
        expenseList.add(ALICE_EXPENSE);
        expenseList.setExpense(ALICE_EXPENSE, BOB_EXPENSE);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(BOB_EXPENSE);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void remove_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.remove(null));
    }

    @Test
    public void remove_expenseDoesNotExist_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> expenseList.remove(ALICE_EXPENSE));
    }

    @Test
    public void remove_existingExpense_removesExpense() {
        expenseList.add(ALICE_EXPENSE);
        expenseList.remove(ALICE_EXPENSE);
        ExpenseList expectedExpenseList = new ExpenseList();
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpenses_nullExpenseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> expenseList.setExpenses((ExpenseList) null));
    }

    @Test
    public void setExpenses_expenseList_replacesOwnListWithProvidedExpenseList() {
        expenseList.add(ALICE_EXPENSE);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(BOB_EXPENSE);
        expenseList.setExpenses(expectedExpenseList);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpenses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpenses((List<Expense>) null));
    }

    @Test
    public void setExpenses_list_replacesOwnListWithProvidedList() {
        expenseList.add(ALICE_EXPENSE);
        List<Expense> expenseList = Collections.singletonList(BOB_EXPENSE);
        this.expenseList.setExpenses(expenseList);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(BOB_EXPENSE);
        assertEquals(expectedExpenseList, this.expenseList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> expenseList.asUnmodifiableObservableList().remove(0));
    }
}
