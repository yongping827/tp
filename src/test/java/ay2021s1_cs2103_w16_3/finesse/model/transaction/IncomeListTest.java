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

public class IncomeListTest {

    private static final Income ALICE_INCOME = new TransactionBuilder(ALICE).buildIncome();
    private static final Income BOB_INCOME = new TransactionBuilder(BOB).buildIncome();

    private final IncomeList incomeList = new IncomeList();

    @Test
    public void add_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.add(null));
    }

    @Test
    public void setIncome_nullTargetIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.setIncome(null, ALICE_INCOME));
    }

    @Test
    public void setIncome_nullEditedIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.setIncome(ALICE_INCOME, null));
    }

    @Test
    public void setIncome_targetIncomeNotInList_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> incomeList.setIncome(ALICE_INCOME, ALICE_INCOME));
    }

    @Test
    public void setIncome_editedIncomeIsSameIncome_success() {
        incomeList.add(ALICE_INCOME);
        incomeList.setIncome(ALICE_INCOME, ALICE_INCOME);
        IncomeList expectedIncomeList = new IncomeList();
        expectedIncomeList.add(ALICE_INCOME);
        assertEquals(expectedIncomeList, incomeList);
    }

    @Test
    public void setIncome_editedIncomeHasSameIdentity_success() {
        incomeList.add(ALICE_INCOME);
        Income editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).buildIncome();
        incomeList.setIncome(ALICE_INCOME, editedAlice);
        IncomeList expectedIncomeList = new IncomeList();
        expectedIncomeList.add(editedAlice);
        assertEquals(expectedIncomeList, incomeList);
    }

    @Test
    public void setIncome_editedIncomeHasDifferentIdentity_success() {
        incomeList.add(ALICE_INCOME);
        incomeList.setIncome(ALICE_INCOME, BOB_INCOME);
        IncomeList expectedIncomeList = new IncomeList();
        expectedIncomeList.add(BOB_INCOME);
        assertEquals(expectedIncomeList, incomeList);
    }

    @Test
    public void remove_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.remove(null));
    }

    @Test
    public void remove_incomeDoesNotExist_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> incomeList.remove(ALICE_INCOME));
    }

    @Test
    public void remove_existingIncome_removesIncome() {
        incomeList.add(ALICE_INCOME);
        incomeList.remove(ALICE_INCOME);
        IncomeList expectedIncomeList = new IncomeList();
        assertEquals(expectedIncomeList, incomeList);
    }

    @Test
    public void setIncomes_nullIncomeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> incomeList.setIncomes((IncomeList) null));
    }

    @Test
    public void setIncomes_incomeList_replacesOwnListWithProvidedIncomeList() {
        incomeList.add(ALICE_INCOME);
        IncomeList expectedIncomeList = new IncomeList();
        expectedIncomeList.add(BOB_INCOME);
        incomeList.setIncomes(expectedIncomeList);
        assertEquals(expectedIncomeList, incomeList);
    }

    @Test
    public void setIncomes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.setIncomes((List<Income>) null));
    }

    @Test
    public void setIncomes_list_replacesOwnListWithProvidedList() {
        incomeList.add(ALICE_INCOME);
        List<Income> incomeList = Collections.singletonList(BOB_INCOME);
        this.incomeList.setIncomes(incomeList);
        IncomeList expectedIncomeList = new IncomeList();
        expectedIncomeList.add(BOB_INCOME);
        assertEquals(expectedIncomeList, this.incomeList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> incomeList.asUnmodifiableObservableList().remove(0));
    }
}
