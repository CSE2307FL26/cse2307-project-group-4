# project26

237 Bank Application

Project Description

The 237 Bank Application is a java command-line banking system.

Our application allows customers to manage bank accounts, make deposits and withdrawals of money, view their account balances, review transaction history, create additional accounts, transfer money, and close their accounts. We also created administrative actions for collecting account fees and interest payments.

For iteration 1, we implemented the following user stories.

Issue 1 - Withdraw Money: Customers can withdraw money from their active account. Withdrawals are validated to prevent taking more than their account balance.

Issue 2 - Check Account Balance: Customers can select an account and view its balance.

Issue 3 - View Transaction History: Customers can view recorded transactions for their active account. Successful deposits and withdrawals are recorded with their type, amount, and timestamp.

Issue 4 - Create Additional Accounts: Customers can create additional bank accounts. Each account begins with a $0 balance and has an indendent balance.

Issue 5 - Close an Account: Customers can close one of their accounts.

Issue 6 - Transer Money: Customers can transfer funds from their active account to another account. A transfer requires sufficient funds and a valid account destination

Issue 7 - Collect Account Fees: Administrators can take fees from the active account. Fees cannot exceed the available balace.

Issue 8 - Add Interest Payments: Administrators can add interest payments to the active account.

For our next iteration we are looking to improve the integration of our current features based on feedback and some of its usability. 

Planned Improvements:

Allow customers to select which account to be main account for specific operations.
Expand transaction history to see fees and interest.
Improve user interface and account management.
Allow customers to choose any account to work on instead of through primary account.

Compiling and Running

From the root directory run:
bash runApp.sh

This will compile the Java source files and launch MainMenu. 

Development

Jon - Issues 7 and 8 (Admin).

Justin - Issues 5 and 6 (Transfer and close account).

Logan - Issues 3 and 4 (Transaction history and create accounts).

Henry - Issues 1 and 2 (Withdrawal and Account balance).

We worked on our issues in seperate branches, then pushed and reviewed code, merged resolving any conflicts, and created our current iteration and script.