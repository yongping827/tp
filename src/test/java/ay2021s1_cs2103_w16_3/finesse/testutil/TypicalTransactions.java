package ay2021s1_cs2103_w16_3.finesse.testutil;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_FRIEND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {

    public static final Transaction ALICE = new TransactionBuilder().withTitle("Alice Pauline")
            .withDate("06/09/2020").withAmount("$1").withCategories("friends").build();
    public static final Transaction BENSON = new TransactionBuilder().withTitle("Benson Meier")
            .withDate("05/10/2020").withAmount("2").withCategories("owesMoney", "friends").build();
    public static final Transaction CARL = new TransactionBuilder().withTitle("Carl Kurz").withAmount("3.50")
            .withDate("06/10/2020").build();
    public static final Transaction DANIEL = new TransactionBuilder().withTitle("Daniel Meier").withAmount("4")
            .withDate("06/10/2020").withCategories("friends").build();
    public static final Transaction ELLE = new TransactionBuilder().withTitle("Elle Meyer").withAmount("5")
            .withDate("06/10/2020").build();
    public static final Transaction FIONA = new TransactionBuilder().withTitle("Fiona Kunz").withAmount("6")
            .withDate("06/10/2020").build();
    public static final Transaction GEORGE = new TransactionBuilder().withTitle("George Best").withAmount("7")
            .withDate("06/10/2020").build();

    // Manually added
    public static final Transaction HOON = new TransactionBuilder().withTitle("Hoon Meier").withAmount("8")
            .withDate("06/10/2020").build();
    public static final Transaction IDA = new TransactionBuilder().withTitle("Ida Mueller").withAmount("9")
            .withDate("06/10/2020").build();

    // Manually added - Transaction's details found in {@code CommandTestUtil}
    public static final Transaction AMY = new TransactionBuilder().withTitle(VALID_TITLE_AMY)
            .withAmount(VALID_AMOUNT_AMY).withDate(VALID_DATE_AMY).withCategories(VALID_CATEGORY_FRIEND).build();
    public static final Transaction BOB = new TransactionBuilder().withTitle(VALID_TITLE_BOB)
            .withAmount(VALID_AMOUNT_BOB).withDate(VALID_DATE_BOB)
            .withCategories(VALID_CATEGORY_HUSBAND, VALID_CATEGORY_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTransactions() {} // prevents instantiation

    /**
     * Returns an {@code FinanceTracker} with all the typical transactions.
     */
    public static FinanceTracker getTypicalFinanceTracker() {
        FinanceTracker ft = new FinanceTracker();
        for (Transaction transaction : getTypicalTransactions()) {
            ft.addTransaction(transaction);
            ft.addExpense(new TransactionBuilder(transaction).buildExpense());
            ft.addIncome(new TransactionBuilder(transaction).buildIncome());
        }
        return ft;
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Expense> getTypicalExpenses() {
        return getTypicalTransactions().stream()
                .map(TransactionBuilder::new)
                .map(TransactionBuilder::buildExpense)
                .collect(Collectors.toList());
    }
    public static List<Income> getTypicalIncomes() {
        return getTypicalTransactions().stream()
                .map(TransactionBuilder::new)
                .map(TransactionBuilder::buildIncome)
                .collect(Collectors.toList());
    }
}
