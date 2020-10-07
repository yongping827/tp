package ay2021s1_cs2103_w16_3.finesse.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.ExpenseList;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.IncomeList;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.TransactionList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the finance tracker level
 */
public class FinanceTracker implements ReadOnlyFinanceTracker {

    private final TransactionList transactions;
    private final ExpenseList expenses;
    private final IncomeList incomes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        transactions = new TransactionList();
        expenses = new ExpenseList();
        incomes = new IncomeList();
    }

    public FinanceTracker() {}

    /**
     * Creates an FinanceTracker using the Transactions in the {@code toBeCopied}
     */
    public FinanceTracker(ReadOnlyFinanceTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses.setExpenses(expenses);
    }

    /**
     * Replaces the contents of the income list with {@code incomes}.
     */
    public void setIncomes(List<Income> incomes) {
        this.incomes.setIncomes(incomes);
    }

    /**
     * Resets the existing data of this {@code FinanceTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyFinanceTracker newData) {
        requireNonNull(newData);

        setTransactions(newData.getTransactionList());
        setExpenses(newData.getExpenseList());
        setIncomes(newData.getIncomeList());
    }

    //// transaction-level operations

    /**
     * Adds a transaction to the finance tracker.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Adds an expense to the finance tracker.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Adds an income to the finance tracker.
     */
    public void addIncome(Income income) {
        incomes.add(income);
    }

    /**
     * Replaces the given transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the finance tracker.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);

        transactions.setTransaction(target, editedTransaction);
    }

    /**
     * Replaces the given expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the finance tracker.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireNonNull(editedExpense);

        expenses.setExpense(target, editedExpense);
    }

    /**
     * Replaces the given income {@code target} in the list with {@code editedIncome}.
     * {@code target} must exist in the finance tracker.
     */
    public void setIncome(Income target, Income editedIncome) {
        requireNonNull(editedIncome);

        incomes.setIncome(target, editedIncome);
    }

    /**
     * Removes {@code key} from this {@code FinanceTracker}.
     * {@code key} must exist in the finance tracker.
     */
    public void removeTransaction(Transaction key) {
        transactions.remove(key);
    }

    /**
     * Removes {@code key} from this {@code FinanceTracker}.
     * {@code key} must exist in the finance tracker.
     */
    public void removeExpense(Expense key) {
        expenses.remove(key);
    }

    /**
     * Removes {@code key} from this {@code FinanceTracker}.
     * {@code key} must exist in the finance tracker.
     */
    public void removeIncome(Income key) {
        incomes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return transactions.asUnmodifiableObservableList().size() + " transactions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Income> getIncomeList() {
        return incomes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinanceTracker // instanceof handles nulls
                && transactions.equals(((FinanceTracker) other).transactions)
                && expenses.equals(((FinanceTracker) other).expenses)
                && incomes.equals(((FinanceTracker) other).incomes));
    }

    @Override
    public int hashCode() {
        return transactions.hashCode();
    }
}
