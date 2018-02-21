
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
//import userInterfaceTestbed.UserInterfaceTestbed;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @modifier S . Jyothsna
 * 
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_DoubleCalculator = new Label("Double Calculator");
	private Label label_Operand1 = new Label("First operand");
	private Label label_Operand1ErrorTerm = new Label("Error Operand1");
	private TextField text_Operand1 = new TextField();
	private TextField text_Operand1ErrorTerm = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private Label label_Operand2ErrorTerm = new Label("Error Operand2");
	private TextField text_Operand2 = new TextField();
	private TextField text_Operand2ErrorTerm = new TextField();
	private Label label_Result = new Label("Result");
	private Label label_ResultErrorTerm = new Label("Error Result");
	private TextField text_Result = new TextField();
	private TextField text_ResultErrorTerm = new TextField();
	
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("\u2212");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_sqrt = new Button("\u221A");				// The divide symbol: \u221A
	private Label label_errOperand1 = new Label(""); 				// For operand1 label
	private Label error_term1 = new Label("");						// For operand1 error Term
	private Label label_errOperand2 = new Label("");				// For operand2 label
	private Label error_term2 = new Label("");
	private Label label_errResult = new Label("");
	private Label result_error = new Label();
	private Text errMVPart1 = new Text();
	private Text errMVPart2 = new Text();
	private Text errMVPart3 = new Text();
	private Text errMVPart4 = new Text();
	private TextFlow errMeasuredValue1;
	private TextFlow errMeasuredValue2;
	private Text errETPart1 = new Text();
	private Text errETPart2 = new Text();
	private Text errETPart3 = new Text();
	private Text errETPart4 = new Text();
	private TextFlow errMeasuredValue3;
	private TextFlow errMeasuredValue4;

	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************
	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_DoubleCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 24, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 50);
		
		setupLabelUI(label_Operand1ErrorTerm, "Arial", 24, Calculator.WINDOW_WIDTH-120, Pos.BASELINE_RIGHT, 10, 50);
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 24, Calculator.WINDOW_WIDTH-370, Pos.BASELINE_LEFT, 10, 80, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		setupTextUI(text_Operand1ErrorTerm, "Arial", 24, 190, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 120, 80, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {seterrOperand1(); });
		
		text_Operand1ErrorTerm.setOnAction((event) -> { text_Operand2ErrorTerm.requestFocus(); });

		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 145);
		label_errOperand1.setTextFill(Color.RED);
		
		
		setupLabelUI(error_term1, "Arial", 18, Calculator.WINDOW_WIDTH, Pos.BASELINE_LEFT, 530, 150); // Label initialization for errorterm1
		error_term1.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 24, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 175);
		
		setupLabelUI(label_Operand2ErrorTerm, "Arial", 24, Calculator.WINDOW_WIDTH-120, Pos.BASELINE_RIGHT, 10, 175);
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 24, Calculator.WINDOW_WIDTH-370, Pos.BASELINE_LEFT, 10, 205, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		
		setupTextUI(text_Operand2ErrorTerm, "Arial", 24, 190, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 120, 205, true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {seterrOperand2(); });
		text_Operand2ErrorTerm.setOnAction((event) -> { text_ResultErrorTerm.requestFocus(); });

		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 272);
		label_errOperand2.setTextFill(Color.RED);
		
		setupLabelUI(error_term2, "Arial", 18, Calculator.WINDOW_WIDTH, Pos.BASELINE_LEFT, 530, 272);
		error_term2.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 24, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 300);
		
		setupLabelUI(label_ResultErrorTerm, "Arial", 24, Calculator.WINDOW_WIDTH-160, Pos.BASELINE_RIGHT, 10, 300);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 24, Calculator.WINDOW_WIDTH-370, Pos.BASELINE_LEFT, 10, 330, false);
		
		setupTextUI(text_ResultErrorTerm, "Arial", 24, 190, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 120, 330, false);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 360); // Label initialization for Operand 1 
		label_errResult.setTextFill(Color.RED);
		
		setupLabelUI(result_error, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 360); // Label initialization for Operand 1 
		result_error.setTextFill(Color.RED);
		
		
		
		/* This block Establishes the two moving parts of the error message for Operand1 and Operand2 */
		
			
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue1 = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue1.setLayoutX(24);  
		errMeasuredValue1.setLayoutY(120);
		
		errMVPart3.setFill(Color.BLACK);
	    errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    errMVPart4.setFill(Color.RED);
	    errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue2 = new TextFlow(errMVPart3, errMVPart4);
		errMeasuredValue2.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue2.setLayoutX(24);  
		errMeasuredValue2.setLayoutY(245);
		
		/*This block Establishes the two moving parts of the error message for the error terms of Operand1 and Operand2*/
		
		errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue3 = new TextFlow(errETPart1, errETPart2);
		errMeasuredValue3.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue3.setLayoutX(535);  
		errMeasuredValue3.setLayoutY(120);
		
		errETPart3.setFill(Color.BLACK);
	    errETPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    errETPart4.setFill(Color.RED);
	    errETPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue4 = new TextFlow(errETPart3, errETPart4);
		errMeasuredValue4.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue4.setLayoutX(535);  
		errMeasuredValue4.setLayoutY(245);
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 425);
		button_Add.setOnAction((event) -> { addOperands(); addOperands_err(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 425);
		button_Sub.setOnAction((event) -> { subOperands(); subOperands_err(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 425);
		button_Mpy.setOnAction((event) -> { mpyOperands(); mpyOperands_err(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 425);
		button_Div.setOnAction((event) -> { divOperands(); divOperands_err(); });
		
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 425);
		button_sqrt.setOnAction((event) -> { sqrtOperands(); });
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DoubleCalculator, label_Operand1,label_Operand1ErrorTerm, text_Operand1,text_Operand1ErrorTerm, label_errOperand1, error_term1,
				label_Operand2,label_Operand2ErrorTerm, text_Operand2, text_Operand2ErrorTerm, label_errOperand2, error_term2, label_Result,label_ResultErrorTerm, text_Result, text_ResultErrorTerm, label_errResult, 
				button_Add, button_Sub, button_Mpy, button_Div,button_sqrt,errMeasuredValue1,errMeasuredValue2,errMeasuredValue3,errMeasuredValue4);//added button_square root for sqare_root button

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************
	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		errMVPart1.setText("");
		errMVPart2.setText("");
		label_errOperand1.setText("");
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			//label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
			label_errOperand1.setText(performGo());
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		errMVPart3.setText("");
		errMVPart4.setText("");
		label_errOperand2.setText("");
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			//label_errOperand2.setText(perform.getOperand2ErrorMessage());
			label_errOperand2.setText(performGo_2());
	}
	
	
	
	private void seterrOperand1() {
		errETPart1.setText("");
		errETPart2.setText("");
		error_term1.setText("");
		text_ResultErrorTerm.setText("");								// Any change of an operand probably invalidates
		label_ResultErrorTerm.setText("Error Result");						// the result, so we clear the old result.
		result_error.setText("");
		if (perform.seterrOperand1(text_Operand1ErrorTerm.getText())) {	// Set the operand and see if there was an error
			error_term1.setText("");					// If no error, clear this operands error
			if (text_Operand2ErrorTerm.getText().length() == 0)		// If the other operand is empty, clear its error
				error_term2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			//label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
			error_term1.setText(performGo_err1());
	}
	
	
	private void seterrOperand2() {
		errETPart3.setText("");
		errETPart4.setText("");
		error_term2.setText("");
		text_ResultErrorTerm.setText("");								// Any change of an operand probably invalidates
		label_ResultErrorTerm.setText("Error Result");						// the result, so we clear the old result.
		result_error.setText("");
		if (perform.seterrOperand2(text_Operand2ErrorTerm.getText())) {	// Set the operand and see if there was an error
			error_term2.setText("");					// If no error, clear this operands error
			if (text_Operand1ErrorTerm.getText().length() == 0)		// If the other operand is empty, clear its error
				error_term1.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			//label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
			error_term2.setText(performGo_err2());
	}
	
	
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	private boolean err_binaryOperandIssues() {
		String errorMessage1 = perform.geterr_Operand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.geterr_Operand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			error_term1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				error_term2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			error_term2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.geterr_Operand1Defined()) {						// Check to see if the first operand is defined
			error_term1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.geterr_Operand2Defined()) {					// Now check the second operand. It is is also
				error_term2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.geterr_Operand2Defined()) {				// If the first is defined, check the second. Both
			error_term2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	
	private boolean Sqrt_binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
//		String errorMessage2 = perform.getOperand2ErrorMessage();
		perform.setOperand1ErrorMessage("");
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
//			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
//				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
//				return true;										// Return true when both operands have errors
//			}
//			else {
//				return true;										// Return true when only the first has an error
//			}
			//text_Operand2.setText("");
			return true;
		}
		
//		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
//			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
//			return true;											// message and return true... the second has an error
//		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
//			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
//				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
//				return true;										// Signal there are issues
//			}
			return true;
		} 
//		else if (!perform.getOperand2Defined())                     // If the first is defined, check the second. Both
//		{				
//			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
//			return true;											// Signal there are issues
//		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	
	
	
	

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		                              // It is to clear the text for error message at operand2
		
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		label_errOperand2.setText(""); 
		label_errOperand1.setText("");
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Sum");								// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}
	
	
	
	
	private void addOperands_err(){
        // It is to clear the text for error message at operand2

			// Check to see if both operands are defined and valid
			if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
			error_term1.setText(""); 
			error_term2.setText("");
			// If the operands are defined and valid, request the business logic method to do the addition and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String answer = perform.err_addition();						// Call the business logic add method
			result_error.setText("");									// Reset any result error messages from before
			if (answer.length() > 0) {								// Check the returned String to see if it is okay
				System.out.println("Error"+answer);
			text_ResultErrorTerm.setText(answer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Sum"
			}
			else {														// Some error occurred while doing the addition.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			result_error.setText(perform.geterr_ResultErrorMessage());	// Display the error message.
			}
}
	
            

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		//label_errOperand2.setText("");                                       // It is to clear the text for error message at operand2
		// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				label_errOperand2.setText(""); 
				label_errOperand1.setText("");
				// If the operands are defined and valid, request the business logic method to do the subtraction and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.subtraction();						// Call the business logic add method
				label_errResult.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("difference");								// change the title of the field to "Minus"
				}
				else {														// Some error occurred while doing the subtraction.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.geterr_ResultErrorMessage());	// Display the error message.
				}									// required to do subtraction.
	}
	
	private void subOperands_err(){
        // It is to clear the text for error message at operand2

			// Check to see if both operands are defined and valid
			if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
			error_term1.setText(""); 
			error_term2.setText("");
			// If the operands are defined and valid, request the business logic method to do the addition and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String theAnswer = perform.err_subtraction();						// Call the business logic add method
			result_error.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_ResultErrorTerm.setText(theAnswer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Sum"
			}
			else {														// Some error occurred while doing the addition.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			result_error.setText(perform.getResultErrorMessage());	// Display the error message.
			}
}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		//label_errOperand2.setText("");                                      // It is to clear the text for error message at operand2
		// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				label_errOperand2.setText(""); 
				label_errOperand1.setText("");
				// If the operands are defined and valid, request the business logic method to do the multiplication and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.multiplication();						// Call the business logic add method
				label_errResult.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("Product");								// change the title of the field to "Product"
				}
				else {														// Some error occurred while doing the multiplication.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}										// required to do multiplication.
	}
	
	private void mpyOperands_err(){
        // It is to clear the text for error message at operand2

			// Check to see if both operands are defined and valid
			if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
			error_term1.setText(""); 
			error_term2.setText("");
			// If the operands are defined and valid, request the business logic method to do the addition and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String theAnswer = perform.err_multiplication();						// Call the business logic add method
			result_error.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_ResultErrorTerm.setText(theAnswer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Sum"
			}
			else {														// Some error occurred while doing the addition.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			result_error.setText(perform.getResultErrorMessage());	// Display the error message.
			}
}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		//label_errOperand2.setText("");                              // It is to clear the text for error message at operand2 
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		label_errOperand2.setText(""); 
		label_errOperand1.setText("");
		// If the operands are defined and valid, request the business logic method to do the division and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.division();						// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
//		if(perform.getOperand2ErrorMessage().length()>0)
//		{
//			label_errOperand2.setText(perform.getOperand2ErrorMessage());
//		}
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("quotient");								// change the title of the field to "Quotient"
		}
		else {														// Some error occurred while doing the division.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errOperand2.setText(perform.getResultErrorMessage());	// Display the error message beside label of operand2.
			//perform.setOperand2ErrorMessage("");
		}	
		
//		label_Result.setText("Division not yet implemented!");		// Replace this line with the code
//		text_Result.setText("");										// required to do division.
	}
	
	private void divOperands_err(){
		//label_errOperand2.setText("");                              // It is to clear the text for error message at operand2 
		if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		error_term1.setText(""); 
		error_term2.setText("");
		// If the operands are defined and valid, request the business logic method to do the division and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.err_division();						// Call the business logic add method
		result_error.setText("");									// Reset any result error messages from before
//		if(perform.getOperand2ErrorMessage().length()>0)
//		{
//			label_errOperand2.setText(perform.getOperand2ErrorMessage());
//		}
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_ResultErrorTerm.setText(theAnswer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Quotient"
		}
		else {														// Some error occurred while doing the division.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			error_term2.setText(perform.getResultErrorMessage());	// Display the error message beside label of operand2.
			//perform.setOperand2ErrorMessage("");
		}	
		
//		label_Result.setText("Division not yet implemented!");		// Replace this line with the code
//		text_Result.setText("");										// required to do division.
	}
	
	private void sqrtOperands(){
		//label_errOperand2.setText("");                                       // It is to clear the text for error message at operand2
		// Check to see if both operands are defined and valid
		if (Sqrt_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;														// without doing the computation
		label_errOperand2.setText(""); 
		label_errOperand1.setText("");
		
		
		
				// If the operands are defined and valid, request the business logic method to do the subtraction and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.sqrt();						// Call the business logic add method
				label_errResult.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("root");								// change the title of the field to "Minus"
				}
				else {														// Some error occurred while doing the subtraction.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errOperand1.setText(perform.getResultErrorMessage());	// Display the error message.
				}									// required to do subtraction.
	}
	
	
	private String performGo() 
	{
		String errMessage = MeasuredValueRecognizer.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {
//			System.out.println(errMessage);
//			label_errOperand1.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return MeasuredValueRecognizer.measuredValueErrorMessage;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart1.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}return MeasuredValueRecognizer.measuredValueErrorMessage;
//		else { 
//			errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
//			if (errMessage != "") {
//				System.out.println(errMessage);
//				label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
//				String input = ErrorTermRecognizer.errorTermInput;
//				if (ErrorTermRecognizer.errorTermIndexofError <= -1) return;
//				errETPart1.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
//				errETPart2.setText("\u21EB");
//			}
//			
//		}
		
	}
	
	
	private String performGo_err1()
	{
		String errMessage;
		errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
		if (errMessage != "") {
			//System.out.println(errMessage);
			//label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
			String input = ErrorTermRecognizer.errorTermInput;
			if (ErrorTermRecognizer.errorTermIndexofError <= -1) return ErrorTermRecognizer.errorTermErrorMessage;
			errETPart1.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
			errETPart2.setText("\u21EB");
			} return ErrorTermRecognizer.errorTermErrorMessage;
	}
	
	
	private String performGo_err2()
	{
		String errMessage;
		errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand2ErrorTerm.getText());
		if (errMessage != "") {
			//System.out.println(errMessage);
			//label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
			String input = ErrorTermRecognizer.errorTermInput;
			if (ErrorTermRecognizer.errorTermIndexofError <= -1) return ErrorTermRecognizer.errorTermErrorMessage;
			errETPart3.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
			errETPart4.setText("\u21EB");
			} return ErrorTermRecognizer.errorTermErrorMessage;
	}
	
	
	private String performGo_2() 
	{
		String errMessage = MeasuredValueRecognizer.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
//			System.out.println(errMessage);
//			label_errOperand2.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return MeasuredValueRecognizer.measuredValueErrorMessage;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart3.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart4.setText("\u21EB");
		}return MeasuredValueRecognizer.measuredValueErrorMessage;
//		else { 
//			errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
//			if (errMessage != "") {
//				System.out.println(errMessage);
//				label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
//				String input = ErrorTermRecognizer.errorTermInput;
//				if (ErrorTermRecognizer.errorTermIndexofError <= -1) return;
//				errETPart1.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
//				errETPart2.setText("\u21EB");
//			}
//			
//		}
		
	}
}
