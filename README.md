## unsual_spending_kata
This is a top-down ([London school TDD](https://github.com/testdouble/contributing-tests/wiki/London-school-TDD)) implementation of the unsual spending by a given user.<br>
For [more details](https://github.com/softwarecrafters/kata-log/blob/master/_katas/unusual-spending-kata.md) about the unusaul spending kata.

### Summary of unusual spending kata.

- Considers the `user` and user `payments`.
- It `compares` two months payments(current and previous).
- Payments have three `properties` that is `price`, `description` and `category`.
- Payments comparisons are based on `categories`.
- There can be `multiple` payment `categories` for a given user.
- The user spending is only `unusual` if the current month expenditure is `50% more` than the previous month.

### Implementation description.

#### Steps.

1. Fetch the payments for the previous and current month based on categories.
2. Compare the paid amount or expenditures for each category basing on the two months.
3. If the expenditure for this month (current) is 50% more than the last month (previous), <br>
  then compose an email for that specfic category.

### Technologies

- java (maven)

### Run project
