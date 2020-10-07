package ay2021s1_cs2103_w16_3.finesse.model;

import static ay2021s1_cs2103_w16_3.finesse.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.ALICE;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.TitleContainsKeywordsPredicate;
import ay2021s1_cs2103_w16_3.finesse.testutil.FinanceTrackerBuilder;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FinanceTracker(), new FinanceTracker(modelManager.getFinanceTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFinanceTrackerFilePath(Paths.get("finance/tracker/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFinanceTrackerFilePath(Paths.get("new/finance/tracker/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFinanceTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFinanceTrackerFilePath(null));
    }

    @Test
    public void setFinanceTrackerFilePath_validPath_setsFinanceTrackerFilePath() {
        Path path = Paths.get("finance/tracker/file/path");
        modelManager.setFinanceTrackerFilePath(path);
        assertEquals(path, modelManager.getFinanceTrackerFilePath());
    }

    @Test
    public void getFilteredTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTransactionList().remove(0));
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpenseList().remove(0));
    }

    @Test
    public void getFilteredIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredIncomeList().remove(0));
    }

    @Test
    public void equals() {
        FinanceTracker financeTracker = new FinanceTrackerBuilder().withTransaction(ALICE).withTransaction(BENSON)
                .withExpense(new TransactionBuilder(ALICE).buildExpense())
                .withExpense(new TransactionBuilder(BENSON).buildExpense())
                .withIncome(new TransactionBuilder(ALICE).buildIncome())
                .withIncome(new TransactionBuilder(BENSON).buildIncome()).build();
        FinanceTracker differentFinanceTracker = new FinanceTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(financeTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(financeTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different financeTracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFinanceTracker, userPrefs)));

        String[] keywords = ALICE.getTitle().fullTitle.split("\\s+");

        // different filteredTransactionList -> returns false
        modelManager.updateFilteredTransactionList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(financeTracker, userPrefs)));

        // different filteredExpenseList -> returns false
        modelManager.updateFilteredExpenseList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(financeTracker, userPrefs)));

        // different filteredIncomeList -> returns false
        modelManager.updateFilteredIncomeList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(financeTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFinanceTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(financeTracker, differentUserPrefs)));
    }
}
