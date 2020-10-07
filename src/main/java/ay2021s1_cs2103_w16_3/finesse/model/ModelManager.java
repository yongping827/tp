package ay2021s1_cs2103_w16_3.finesse.model;

import static ay2021s1_cs2103_w16_3.finesse.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;
import ay2021s1_cs2103_w16_3.finesse.commons.core.LogsCenter;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the finance tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FinanceTracker financeTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Transaction> filteredTransactions;
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<Income> filteredIncomes;

    /**
     * Initializes a ModelManager with the given financeTracker and userPrefs.
     */
    public ModelManager(ReadOnlyFinanceTracker financeTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(financeTracker, userPrefs);

        logger.fine("Initializing with finance tracker: " + financeTracker + " and user prefs " + userPrefs);

        this.financeTracker = new FinanceTracker(financeTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTransactions = new FilteredList<>(this.financeTracker.getTransactionList());
        filteredExpenses = new FilteredList<>(this.financeTracker.getExpenseList());
        filteredIncomes = new FilteredList<>(this.financeTracker.getIncomeList());
    }

    public ModelManager() {
        this(new FinanceTracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFinanceTrackerFilePath() {
        return userPrefs.getFinanceTrackerFilePath();
    }

    @Override
    public void setFinanceTrackerFilePath(Path financeTrackerFilePath) {
        requireNonNull(financeTrackerFilePath);
        userPrefs.setFinanceTrackerFilePath(financeTrackerFilePath);
    }

    //=========== FinanceTracker ================================================================================

    @Override
    public void setFinanceTracker(ReadOnlyFinanceTracker financeTracker) {
        this.financeTracker.resetData(financeTracker);
    }

    @Override
    public ReadOnlyFinanceTracker getFinanceTracker() {
        return financeTracker;
    }

    @Override
    public void deleteTransaction(Transaction target) {
        financeTracker.removeTransaction(target);
    }

    @Override
    public void deleteExpense(Expense target) {
        financeTracker.removeExpense(target);
    }

    @Override
    public void deleteIncome(Income target) {
        financeTracker.removeIncome(target);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        financeTracker.addTransaction(transaction);
        updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
    }

    @Override
    public void addExpense(Expense expense) {
        financeTracker.addExpense(expense);
        updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
    }

    @Override
    public void addIncome(Income income) {
        financeTracker.addIncome(income);
        updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
    }

    @Override
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireAllNonNull(target, editedTransaction);

        financeTracker.setTransaction(target, editedTransaction);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        financeTracker.setExpense(target, editedExpense);
    }

    @Override
    public void setIncome(Income target, Income editedIncome) {
        requireAllNonNull(target, editedIncome);

        financeTracker.setIncome(target, editedIncome);
    }

    //=========== Filtered Transaction List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Transaction} backed by the internal list of
     * {@code versionedFinanceTracker}
     */
    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return filteredTransactions;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal list of
     * {@code versionedFinanceTracker}
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Income} backed by the internal list of
     * {@code versionedFinanceTracker}
     */
    @Override
    public ObservableList<Income> getFilteredIncomeList() {
        return filteredIncomes;
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredTransactions.setPredicate(predicate);
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    @Override
    public void updateFilteredIncomeList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredIncomes.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return financeTracker.equals(other.financeTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredTransactions.equals(other.filteredTransactions)
                && filteredExpenses.equals(other.filteredExpenses)
                && filteredIncomes.equals(other.filteredIncomes);
    }

}
