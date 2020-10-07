package ay2021s1_cs2103_w16_3.finesse.testutil;

import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * A utility class to help with building FinanceTracker objects.
 */
public class FinanceTrackerBuilder {

    private FinanceTracker financeTracker;

    public FinanceTrackerBuilder() {
        financeTracker = new FinanceTracker();
    }

    public FinanceTrackerBuilder(FinanceTracker financeTracker) {
        this.financeTracker = financeTracker;
    }

    /**
     * Adds a new {@code Transaction} to the {@code FinanceTracker} that we are building.
     */
    public FinanceTrackerBuilder withTransaction(Transaction transaction) {
        financeTracker.addTransaction(transaction);
        return this;
    }

    /**
     * Adds a new {@code Transaction} to the {@code FinanceTracker} that we are building.
     */
    public FinanceTrackerBuilder withExpense(Expense expense) {
        financeTracker.addExpense(expense);
        return this;
    }

    /**
     * Adds a new {@code Transaction} to the {@code FinanceTracker} that we are building.
     */
    public FinanceTrackerBuilder withIncome(Income income) {
        financeTracker.addIncome(income);
        return this;
    }

    public FinanceTracker build() {
        return financeTracker;
    }
}
