/**
 * Created by FZL on 6/18/2016.
 */
package com.banker;

import javax.swing.JOptionPane;

import com.utilities.BankMethods;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NewFXBanker extends Application {
	BankMethods banker = new BankMethods();

	Stage chkAddStage = new Stage();
 
	/**
	 * Build menu bar with included menus for this demonstration.
	 */
	private MenuBar buildMenuBarWithMenus(final ReadOnlyDoubleProperty menuWidthProperty, Stage stage) {

		MenuBar menuBar = new MenuBar();

		// Prepare left-most 'File' drop-down menu
		Menu files = new Menu("Files");
		MenuItem[] fileNames = new MenuItem[13];
		fileNames[0] = new MenuItem("Create New Data System");
		fileNames[1] = new MenuItem("Load All Data");
		fileNames[2] = new MenuItem("Save All Data");
		files.getItems().addAll(fileNames[0], fileNames[1], fileNames[2]);


		menuBar.getMenus().add(files);

		Menu accountMenu = new Menu("New");
		MenuItem accountCustomer = new MenuItem("Customers");
		MenuItem accountChecking = new MenuItem("Checking");
		MenuItem accountGold = new MenuItem("Gold");
		MenuItem accountRegular = new MenuItem("Regular");
		//SubMenuItem subM = new SubMenuItem("This is it");

		accountMenu.getItems().addAll(accountCustomer, new SeparatorMenuItem(), accountChecking, accountGold, accountRegular);
		menuBar.getMenus().add(accountMenu);

		// Prepare 'Examples' drop-down menu

		Menu reports = new Menu("Reports");
		MenuItem rptCustomer = new MenuItem("Customers");
		MenuItem rptAccounts = new MenuItem("Accounts");
		MenuItem rptTransaction = new MenuItem("Transactions");
		MenuItem rptStatistics = new MenuItem("Statistics");

		reports.getItems().addAll(rptCustomer, rptAccounts, rptTransaction, rptStatistics);
		menuBar.getMenus().add(reports);

		// Prepare transaction drop-down menu

		Menu transactions = new Menu("Transactions");
		MenuItem tranDeposit = new MenuItem("Deposit");
		MenuItem tranWithdraw = new MenuItem("Withdrawal");
		transactions.getItems().addAll(tranDeposit, tranWithdraw);
		menuBar.getMenus().add(transactions);

		Menu remove = new Menu("Remove");
		MenuItem removeCustomer = new MenuItem("Remove Customer");
		MenuItem removeAccount = new MenuItem("Remove Account");
		remove.getItems().addAll(removeCustomer, removeAccount);
		menuBar.getMenus().add(remove);

		Menu quit = new Menu("Quit");
		quit.getItems().add(new MenuItem("Exit and Save"));
		menuBar.getMenus().add(quit);



		// ************************************************************ File System ************************************************************

		// ************************** create new system **************************
		fileNames[0].setOnAction(e -> {
			banker.createNewSystemData();
			stage.setTitle(banker.bankName);
		});

		// ************************** load all data **************************
		fileNames[1].setOnAction(e -> {
			banker.loadConfigData();
			banker.loadCustomerObjectData();
			banker.loadAccountObjectData();
			banker.loadTransactionObjectData();
			stage.setTitle(banker.bankName);
		});

		// ************************** save all data **************************
		fileNames[2].setOnAction(e -> {
			banker.saveConfigData();
			banker.saveCustomerObjectData();
			banker.saveAccountObjectData();
			banker.saveTransactionObjectData();
		});



		// ************************************* quit and save *************************************
		quit.setOnAction(e -> {
			banker.saveConfigData();
			banker.saveCustomerObjectData(); 
			banker.saveAccountObjectData();
			banker.saveTransactionObjectData();
			System.exit(0);
		});

		// ************************************************************ report customers ************************************************************
		rptCustomer.setOnAction(e -> {
			if (banker.customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no Customers to display! Terminating  view Customers!", "No Customers", JOptionPane.INFORMATION_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.generateReport(0), 1000, 500);
				chkAddStage.setTitle("Generate Reports");
				chkAddStage.setScene(myScene);
				chkAddStage.show();
			}
		});

		// ************************************************************ report accounts ************************************************************

		rptAccounts.setOnAction(e -> {
			if (banker.accounts.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no Accounts to display! Terminating  view Accounts!", "No Accounts", JOptionPane.INFORMATION_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.generateReport(1), 1300, 500);
				chkAddStage.setTitle("Generate Reports");
				chkAddStage.setScene(myScene);
				chkAddStage.show();
			}
		});

		// ************************************************************ report transaction ************************************************************
		rptTransaction.setOnAction(e -> {
			if (banker.transactions.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no Transactions to display! Terminating  view Transactions!", "No Customers", JOptionPane.INFORMATION_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.generateReport(2), 1300, 500);
				chkAddStage.setTitle("Generate Reports");
				chkAddStage.setScene(myScene);
				chkAddStage.show();
			}
		});

		// ************************************************************ report statistics ************************************************************
		rptStatistics.setOnAction(e -> {
			if (banker.accounts.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no Accounts to display! Terminating view Statistics!", "No Accounts", JOptionPane.INFORMATION_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.generateReport(3), 1300, 500);
				chkAddStage.setTitle("Generate Reports");
				chkAddStage.setScene(myScene);
				chkAddStage.show();
			}
		});

		// ************************************************************ create new customer ************************************************************
		accountCustomer.setOnAction(e -> {
			chkAddStage.hide();
			Scene myScene = new Scene(banker.getCustPane(), 575, 300, Color.BEIGE);
			chkAddStage.setTitle("Customer Information");
			chkAddStage.setScene(myScene);
			chkAddStage.show();
			// checkFileStatus(fileNames);   	  
		});

		// ************************************************************ create checking account ************************************************************
		accountChecking.setOnAction(e -> {
			chkAddStage.hide();
			if (banker.customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add Checking Account!!");
			} else {
				Scene myScene = new Scene(banker.addAccountPane(0), 800, 525);
				chkAddStage.setTitle("Add Account"); // Set the stage title
				chkAddStage.setScene(myScene); // Place the scene in the stage
				chkAddStage.show(); // Display the stage
			}
		});

		// ************************************************************ create regular account ************************************************************
		accountRegular.setOnAction(e -> {
			chkAddStage.hide();
			if (banker.customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add Regular Account!!");
			} else {
				Scene myScene = new Scene(banker.addAccountPane(1), 800, 525);
				chkAddStage.setTitle("Add Account"); // Set the stage title
				chkAddStage.setScene(myScene); // Place the scene in the stage
				chkAddStage.show(); // Display the stage
			}
		});

		// ************************************************************ create gold account ************************************************************
		accountGold.setOnAction(e -> {
			chkAddStage.hide();
			if (banker.customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add Gold Account!!");
			} else {
				//Stage chkAddStage = new Stage();
				Scene myScene = new Scene(banker.addAccountPane(2), 800, 525);
				chkAddStage.setTitle("Add Account"); // Set the stage title
				chkAddStage.setScene(myScene); // Place the scene in the stage
				chkAddStage.show(); // Display the stage
				//checkFileStatus(fileNames);
			}
		});

		// *********************************************************** Transactions ***********************************************************

		// *********************************** deposit ***********************************
		tranDeposit.setOnAction(e -> {
			if (banker.accounts.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no accounts to add Deposits too!", "Make Deposit", JOptionPane.ERROR_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.processTransactionsPane(0), 800, 450);
				chkAddStage.setTitle("Transactions"); // Set the stage title
				chkAddStage.setScene(myScene); // Place the scene in the stage
				chkAddStage.show(); // Display the stage
			}
		});

		// *********************************** withdrawal ***********************************
		tranWithdraw.setOnAction(e -> {
			if (banker.accounts.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no accounts to take withdrawals from!", "Make Withdrawal", JOptionPane.ERROR_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.processTransactionsPane(1), 800, 450);
				chkAddStage.setTitle("Transactions"); // Set the stage title
				chkAddStage.setScene(myScene); // Place the scene in the stage
				chkAddStage.show(); // Display the stage
			}
		});


		// *********************************************************** Remove ***********************************************************

		// *********************************** Remove Customer ***********************************

		removeCustomer.setOnAction(e -> {
			if (banker.customers.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no Customers to remove!", "Customer Removal", JOptionPane.ERROR_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.removeScreen(0), 800, 450);
				chkAddStage.setTitle("Remove Customer");
				chkAddStage.setScene(myScene);
				chkAddStage.show();
			}
		});

		// *********************************** Remove Account ***********************************

		removeAccount.setOnAction(e -> {
			if (banker.accounts.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There are no Accounts to remove!", "Account Removal", JOptionPane.ERROR_MESSAGE);
			} else {
				chkAddStage.hide();
				Scene myScene = new Scene(banker.removeScreen(1), 800, 450);
				chkAddStage.setTitle("Remove Customer");
				chkAddStage.setScene(myScene);
				chkAddStage.show();
			}
		});

		menuBar.prefWidthProperty().bind(menuWidthProperty);

		return menuBar;
	}


	/**
	 * Start of Bank menu System
	 */
	@Override
	public void start(Stage stage) {
		banker.loadFileName();
		banker.loadConfigData();
		banker.loadCustomerObjectData();
		banker.loadAccountObjectData();
		banker.loadTransactionObjectData();
		banker.checkStatus();
		stage.setTitle(banker.bankName);
		final Group rootGroup = new Group();
		final Scene scene = new Scene(rootGroup, 400, 25, Color.BEIGE);
		final MenuBar menuBar = buildMenuBarWithMenus(stage.widthProperty(), stage);
		rootGroup.getChildren().add(menuBar);
		stage.setScene(scene);
		stage.show();


	}


	/**
	 * Main executable function for running menus
	 */
	public static void main(String[] arguments) {

		Application.launch(arguments);
	}

}
