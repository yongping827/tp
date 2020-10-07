package ay2021s1_cs2103_w16_3.finesse.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Transaction> PREDICATE_SHOW_ALL_TRANSACTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' finance tracker file path.
     */
    Path getFinanceTrackerFilePath();

    /**
     * Sets the user prefs' finance tracker file path.
     */
    void setFinanceTrackerFilePath(Path financeTrackerFilePath);

    /**
     * Replaces finance tracker data with the data in {@code financeTracker}.
     */
    void setFinanceTracker(ReadOnlyFinanceTracker financeTracker);

    /** Returns the FinanceTracker */
    ReadOnlyFinanceTracker getFinanceTracker();

    /**
     * Deletes the given transaction.
     * The transaction must exist in the finance tracker.
     */
    void deleteTransaction(Transaction target);

    /**
     * Deletes the given expense.
     * The expense must exist in the finance tracker.
     */
    void deleteExpense(Expense target);

    /**
     * Deletes the given income.
     * The income must exist in the finance tracker.
     */
    void deleteIncome(Income target);

    /**
     * Adds the given transaction.
     */
    void addTransaction(Transaction transaction);

    /**
     * Adds the given expense.
     */
    void addExpense(Expense expense);

    /**
     * Adds the given income.
     */
    void addIncome(Income income);

    /**
     * Replaces the given transaction {@code target} with {@code editedTransaction}.
     * {@code target} must exist in the finance tracker.
     */
    void setTransaction(Transaction target, Transaction editedTransaction);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the finance tracker.
     */
    void setExpense(Expense target, Expense editedExpense);

    /**
     * Replaces the given income {@code target} with {@code editedIncome}.
     * {@code target} must exist in the finance tracker.
     */
    void setIncome(Income target, Income editedIncome);

    /** Returns an unmodifiable view of the filtered transaction list */
    ObservableList<Transaction> getFilteredTransactionList();

    /** Returns an unmodifiable view of the filtered expense list */
    ObservableList<Expense> getFilteredExpenseList();

    /** Returns an unmodifiable view of the filtered income list */
    ObservableList<Income> getFilteredIncomeList();

    /**
     * Updates the filter of the filtered transaction list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTransactionList(Predicate<Transaction> predicate);

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpenseList(Predicate<Transaction> predicate);

    /**
     * Updates the filter of the filtered income list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIncomeList(Predicate<Transaction> predicate);
}
